CREATE TABLE `apply` (
  `id` varchar(10) NOT NULL,
  `applyName` varchar(40) NOT NULL,
  `applyAge` int(10) NOT NULL,
  `applyIdcard` varchar(50) NOT NULL,
  `applyType` varchar(20) NOT NULL,
  `guarantorName` varchar(40) NOT NULL,
  `applyMember` varchar(40) NOT NULL,
  `applyDate` varchar(20) NOT NULL,
  `applyMoney` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8