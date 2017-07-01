CREATE TABLE `flow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `applyId` varchar(10) NOT NULL,
  `flowName` varchar(40) NOT NULL,
  `flowScale` int(10) NOT NULL,
  `flowResult` varchar(50) NOT NULL,
  `flowPrice` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8