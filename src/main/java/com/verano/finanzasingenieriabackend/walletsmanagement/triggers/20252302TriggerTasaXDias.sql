DELIMITER $$

CREATE TRIGGER after_update_wallets
    AFTER UPDATE ON financeyou.wallets
    FOR EACH ROW
BEGIN
    DECLARE v_tasa_efectiva DECIMAL(20,16);
    DECLARE v_plazo_dias INT;
    DECLARE v_nueva_tasa DECIMAL(20,16);
    DECLARE v_old_tasa DECIMAL(20,16);
    DECLARE done INT DEFAULT 0;

    -- Cursor para recorrer las cartas asociadas al wallet actualizado
    DECLARE cur CURSOR FOR
    SELECT l.id, l.plazo_dias_descuento, b.tasa_efectiva_calculada_con_trigger, l.tasa_efectiva_por_dias
    FROM financeyou.letters l
             JOIN financeyou.banks b ON b.id = NEW.bank_id
    WHERE l.wallet_id = NEW.id;

    -- Handler para terminar el cursor
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    OPEN cur;

    read_loop: LOOP
        FETCH cur INTO v_old_tasa, v_plazo_dias, v_tasa_efectiva, v_nueva_tasa;

        IF done THEN
            LEAVE read_loop;
END IF;

-- Calcular la nueva tasa efectiva por días con mayor precisión
SET v_nueva_tasa = POWER((1 + CAST(v_tasa_efectiva AS DECIMAL(20,16))), v_plazo_dias) - 1;

        -- Actualizar la tasa en la tabla letters si cambió
        IF v_old_tasa <> v_nueva_tasa THEN
UPDATE financeyou.letters
SET tasa_efectiva_por_dias = v_nueva_tasa
WHERE wallet_id = NEW.id AND plazo_dias_descuento = v_plazo_dias;
END IF;

END LOOP;

CLOSE cur;

END $$

DELIMITER ;
