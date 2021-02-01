-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 01, 2021 at 05:19 PM
-- Server version: 10.4.10-MariaDB
-- PHP Version: 7.1.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `query_latihan1`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_anggota`
--

CREATE TABLE `tb_anggota` (
  `ID_Anggota` varchar(11) NOT NULL,
  `NM_Anggota` varchar(50) NOT NULL,
  `Alamat` varchar(255) NOT NULL,
  `No_telp` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_anggota`
--

INSERT INTO `tb_anggota` (`ID_Anggota`, `NM_Anggota`, `Alamat`, `No_telp`) VALUES
('A001', 'Permadi', 'Cirebon', '0899'),
('A002', 'Eka', 'Cirebon', '0899'),
('A003', 'Permana', 'Cirebon', '0899');

-- --------------------------------------------------------

--
-- Table structure for table `tb_buku`
--

CREATE TABLE `tb_buku` (
  `ID_Buku` varchar(11) NOT NULL,
  `NM_Buku` varchar(50) NOT NULL,
  `Jenis` varchar(30) NOT NULL,
  `Harga` int(11) NOT NULL,
  `Pengarang` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_buku`
--

INSERT INTO `tb_buku` (`ID_Buku`, `NM_Buku`, `Jenis`, `Harga`, `Pengarang`) VALUES
('B001`', 'Lord of The Ring', 'Novel', 22000, 'J R R Tolkien'),
('B002', 'Harry Potter', 'Novel', 30000, 'JK Rowling');

-- --------------------------------------------------------

--
-- Table structure for table `tb_kembali`
--

CREATE TABLE `tb_kembali` (
  `ID_Pinjam` varchar(11) NOT NULL,
  `Tgl_Kembali` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_kembali`
--

INSERT INTO `tb_kembali` (`ID_Pinjam`, `Tgl_Kembali`) VALUES
('P003', '2011-07-29');

-- --------------------------------------------------------

--
-- Table structure for table `tb_pinjam`
--

CREATE TABLE `tb_pinjam` (
  `ID_Pinjam` varchar(11) NOT NULL,
  `ID_Anggota` varchar(11) NOT NULL,
  `ID_Buku` varchar(11) NOT NULL,
  `Tgl_Pinjam` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_pinjam`
--

INSERT INTO `tb_pinjam` (`ID_Pinjam`, `ID_Anggota`, `ID_Buku`, `Tgl_Pinjam`) VALUES
('P001', 'A001', 'B001`', '2011-07-25'),
('P002', 'A002', 'B002', '2011-07-26'),
('P003', 'A003', 'B001`', '2011-07-25'),
('P004', 'A001', 'B002', '2011-07-25'),
('P005', 'A001', 'B002', '2011-07-26'),
('P006', 'A002', 'B001`', '2011-07-25');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_anggota`
--
ALTER TABLE `tb_anggota`
  ADD PRIMARY KEY (`ID_Anggota`);

--
-- Indexes for table `tb_buku`
--
ALTER TABLE `tb_buku`
  ADD PRIMARY KEY (`ID_Buku`);

--
-- Indexes for table `tb_kembali`
--
ALTER TABLE `tb_kembali`
  ADD PRIMARY KEY (`ID_Pinjam`);

--
-- Indexes for table `tb_pinjam`
--
ALTER TABLE `tb_pinjam`
  ADD PRIMARY KEY (`ID_Pinjam`),
  ADD KEY `ID_Anggota` (`ID_Anggota`),
  ADD KEY `ID_Buku` (`ID_Buku`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_kembali`
--
ALTER TABLE `tb_kembali`
  ADD CONSTRAINT `tb_kembali_ibfk_1` FOREIGN KEY (`ID_Pinjam`) REFERENCES `tb_pinjam` (`ID_Pinjam`);

--
-- Constraints for table `tb_pinjam`
--
ALTER TABLE `tb_pinjam`
  ADD CONSTRAINT `tb_pinjam_ibfk_1` FOREIGN KEY (`ID_Anggota`) REFERENCES `tb_anggota` (`ID_Anggota`),
  ADD CONSTRAINT `tb_pinjam_ibfk_2` FOREIGN KEY (`ID_Buku`) REFERENCES `tb_buku` (`ID_Buku`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
