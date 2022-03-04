drop database i_soap;
CREATE DATABASE IF NOT EXISTS i_soap;

SET GLOBAL validate_password.length = 4;
SET GLOBAL validate_password.policy=LOW;

CREATE USER 'user'@'%' IDENTIFIED BY 'password';
GRANT ALL ON i_soap.* TO 'user'@'%';