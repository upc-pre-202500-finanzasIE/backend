DELIMITER //

CREATE TRIGGER update_wallet_id
    AFTER INSERT ON wallet_letter_ids
    FOR EACH ROW
BEGIN
    UPDATE letters
    SET wallet_id = NEW.wallet_id
    WHERE id = NEW.letter_id;
END;

//

DELIMITER ;
