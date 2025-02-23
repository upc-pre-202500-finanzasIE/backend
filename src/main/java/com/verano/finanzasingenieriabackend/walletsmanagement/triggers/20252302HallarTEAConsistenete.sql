DELIMITER $$

CREATE TRIGGER calcular_tasa_efectiva
    BEFORE INSERT ON banks
    FOR EACH ROW
BEGIN
    IF NEW.tasa_de_interes IS NOT NULL AND NEW.periodo_tasa IS NOT NULL AND NEW.periodo_tasa > 0 THEN
        IF NEW.is_efectiva = 1 THEN
            SET NEW.tasa_efectiva_calculada_con_trigger =
                POW((1 + (NEW.tasa_de_interes / 100)), (360 / NEW.periodo_tasa)) - 1;
    ELSE
            SET NEW.tasa_efectiva_calculada_con_trigger =
                POW((1 + ((NEW.tasa_de_interes / 100) / (NEW.periodo_tasa / NEW.capitalizacion))), (360 / NEW.capitalizacion)) - 1;
END IF;
ELSE
        SET NEW.tasa_efectiva_calculada_con_trigger = NULL;
END IF;
END $$

DELIMITER ;
