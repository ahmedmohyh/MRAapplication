

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE `Movie` (
  `id` int(11) NOT NULL,
  `OriginalPublishingDate` timestamp NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `director` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `ActorList` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `Movie`
  ADD PRIMARY KEY (`id`);
  
ALTER TABLE `Movie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
  
  CREATE TABLE `UserData` (
  `id` int(11) NOT NULL,
  `age` int(11) NOT NULL,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL UNIQUE,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL	
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `UserData`
  ADD PRIMARY KEY (`id`);
  
ALTER TABLE `UserData`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

  CREATE TABLE `Rating` (
  `id` int(11) NOT NULL,
  `rating` int(11) NOT NULL,
  `FilmID` int(11) NOT NULL,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `Rating`
 ADD PRIMARY KEY (`id`);
  
ALTER TABLE `Rating`
  ADD foreign key (`FilmID`) references Movie(`id`);
  
ALTER TABLE `Rating`
  ADD foreign key (`userName`) references UserData(`userName`);
  
ALTER TABLE `Rating`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `Rating`
    ADD CONSTRAINT `Rating` UNIQUE(`userName`, `FilmID`);

  ALTER TABLE `Movie`
  ADD CONSTRAINT `Movie` UNIQUE(`title`, `OriginalPublishingDate`);


COMMIT;