DELIMITER $$

CREATE TRIGGER after_insert_wallet_letter_ids
    AFTER INSERT ON financeyou.wallet_letter_ids
    FOR EACH ROW
BEGIN
    DECLARE v_fecha_descuento DATE;
    DECLARE v_fecha_vencimiento DATE;
    DECLARE v_plazo_dias INT;

    -- Obtener la fecha de descuento desde la tabla wallets
    SELECT fecha_descuento
    INTO v_fecha_descuento
    FROM financeyou.wallets
    WHERE id = NEW.wallet_id
        LIMIT 1;

    -- Obtener la fecha de vencimiento desde la tabla letters
    SELECT fecha_vencimiento
    INTO v_fecha_vencimiento
    FROM financeyou.letters
    WHERE id = NEW.letter_id
        LIMIT 1;

    -- Calcular plazo si ambas fechas existen
    IF v_fecha_descuento IS NOT NULL AND v_fecha_vencimiento IS NOT NULL THEN
        SET v_plazo_dias = DATEDIFF(v_fecha_vencimiento, v_fecha_descuento);

        -- Actualizar la columna plazo_dias_descuento en la tabla letters
    UPDATE financeyou.letters
    SET plazo_dias_descuento = v_plazo_dias
    WHERE id = NEW.letter_id;
END IF;

END $$

DELIMITER ;
