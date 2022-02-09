CREATE TABLE `branch`.`customer` (
  `cid` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(256) NULL,
  `last_name` VARCHAR(256) NULL,
  `address` VARCHAR(256) NULL,
  `timestamp` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`cid`));
  
CREATE TABLE `branch`.`agent_details` (
  `aid` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(256) NULL,
  `last_name` VARCHAR(256) NULL,
  `address` VARCHAR(256) NULL,
  `timestamp` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`aid`));


CREATE TABLE `branch`.`agent_stats` (
  `aid` INT NULL,
  `rating` FLOAT NULL,
  `max_num_customers` INT NULL,
  `timestamp` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  INDEX `aid_idx` (`aid` ASC) VISIBLE,
  CONSTRAINT `aid`
    FOREIGN KEY (`aid`)
    REFERENCES `branch`.`agent_details` (`aid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
  

CREATE TABLE `branch`.`agent_login_status` (
  `aid` INT NULL,
  `login_status` TINYINT NULL,
  `timestamp` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
   INDEX `aid` (`aid` ASC) VISIBLE,
  CONSTRAINT `aid_idx_2`
    FOREIGN KEY (`aid`)
    REFERENCES `branch`.`agent_details` (`aid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


CREATE TABLE `branch`.`message_base` (
  `mid` INT NOT NULL,
  `aid` INT NULL,
  `cid` INT NULL,
  `timestamp` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`mid`),
  INDEX `aid_idx` (`aid` ASC) VISIBLE,
  INDEX `cid_idx` (`cid` ASC) VISIBLE,
  CONSTRAINT `aid_idx`
    FOREIGN KEY (`aid`)
    REFERENCES `branch`.`agent_details` (`aid`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION,
  CONSTRAINT `cid_idx`
    FOREIGN KEY (`cid`)
    REFERENCES `branch`.`customer` (`cid`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION);


CREATE TABLE `branch`.`message_details` (
  `mid` INT NOT NULL,
  `message` VARCHAR(256) NULL,
  `timestamp` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`mid`),
  CONSTRAINT `mid_idx`
    FOREIGN KEY (`mid`)
    REFERENCES `branch`.`message_base` (`mid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE `branch`.`status` (
  `sid` INT NOT NULL AUTO_INCREMENT COMMENT 'Unique ID for status',
  `status_message` VARCHAR(32) NULL COMMENT 'Variety of status for a conversation.',
  PRIMARY KEY (`sid`));

CREATE TABLE `branch`.`message_status` (
  `mid` INT NOT NULL,
  `status` INT NULL,
  `timestamp` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`mid`),
  INDEX `status_idx_idx` (`status` ASC) VISIBLE,
  CONSTRAINT `mid_idx2`
    FOREIGN KEY (`mid`)
    REFERENCES `branch`.`message_base` (`mid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `status_idx`
    FOREIGN KEY (`status`)
    REFERENCES `branch`.`status` (`sid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


