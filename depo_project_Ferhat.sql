-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 04 Eyl 2021, 22:24:02
-- Sunucu sürümü: 10.4.20-MariaDB
-- PHP Sürümü: 7.3.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `depo_project`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `admin`
--

INSERT INTO `admin` (`id`, `email`, `name`, `password`, `surname`) VALUES
(1, 'ferhat@mail.com', 'Ferhat', '827ccb0eea8a706c4c34a16891f84e7b', 'Yıldırım'),
(3, 'ahmet@mail.com', 'Ahmet', '827ccb0eea8a706c4c34a16891f84e7b', 'Şen');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `cashin`
--

CREATE TABLE `cashin` (
  `cash_id` int(11) NOT NULL,
  `cashIn_date` date DEFAULT NULL,
  `cash_status` int(11) NOT NULL,
  `cu_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `payInDetail` varchar(255) DEFAULT NULL,
  `payInTotal` int(11) NOT NULL,
  `order_order_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `cashin`
--

INSERT INTO `cashin` (`cash_id`, `cashIn_date`, `cash_status`, `cu_id`, `order_id`, `payInDetail`, `payInTotal`, `order_order_id`) VALUES
(2, '2021-09-03', 1, 4, 13, 'p2', 4000, 13),
(3, '2021-09-03', 1, 3, 18, 'K', 2000, 18),
(4, '2021-09-03', 1, 4, 15, 'p', 4000, 15),
(5, '2021-09-03', 1, 4, 17, 'u', 1000, 17),
(6, '2021-09-03', 1, 4, 20, 'D', 2000, 20),
(7, '2021-09-03', 1, 4, 20, 'N', 7000, 20),
(9, '2021-09-04', 1, 4, 13, 'L', 1000, 13),
(11, '2021-09-04', 1, 2, 24, 'Gömlek', 20000, 24),
(12, '2021-09-04', 1, 4, 24, 'H', 400, 24);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `cashobject`
--

CREATE TABLE `cashobject` (
  `cu_id` int(11) NOT NULL,
  `fis_no` int(11) NOT NULL,
  `payInDetail` varchar(255) DEFAULT NULL,
  `payInTotal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `cashout`
--

CREATE TABLE `cashout` (
  `cashOut_id` int(11) NOT NULL,
  `payOutDetail` varchar(255) DEFAULT NULL,
  `payOutSection` int(11) NOT NULL,
  `payOutTitle` varchar(255) DEFAULT NULL,
  `payOutTotal` int(11) NOT NULL,
  `payOut_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `cashout`
--

INSERT INTO `cashout` (`cashOut_id`, `payOutDetail`, `payOutSection`, `payOutTitle`, `payOutTotal`, `payOut_date`) VALUES
(1, 'C', 0, 'Cam Kırılması', 1200, '2021-09-03'),
(2, 'T', 4, 'Temizlik Malzemesi', 450, '2021-09-03'),
(3, 'Ahşap', 1, 'Masa', 1000, '2021-09-03'),
(4, 'Tamir', 3, 'Masa kırıldı', 100, '2021-09-03'),
(5, 'Kalem', 1, 'Kalem', 10, '2021-09-04');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `cporder`
--

CREATE TABLE `cporder` (
  `cu_id` int(11) NOT NULL,
  `cu_name` varchar(255) DEFAULT NULL,
  `cu_surname` varchar(255) DEFAULT NULL,
  `fis_no` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `order_size` int(11) NOT NULL,
  `pro_sale_price` int(11) NOT NULL,
  `pro_title` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `customer`
--

CREATE TABLE `customer` (
  `cu_id` int(11) NOT NULL,
  `cu_address` varchar(500) DEFAULT NULL,
  `cu_code` bigint(20) NOT NULL,
  `cu_company_title` varchar(255) DEFAULT NULL,
  `cu_email` varchar(500) DEFAULT NULL,
  `cu_mobile` varchar(255) DEFAULT NULL,
  `cu_name` varchar(255) DEFAULT NULL,
  `cu_password` varchar(32) DEFAULT NULL,
  `cu_phone` varchar(255) DEFAULT NULL,
  `cu_status` int(11) NOT NULL,
  `cu_surname` varchar(255) DEFAULT NULL,
  `cu_tax_administration` varchar(255) DEFAULT NULL,
  `cu_tax_number` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `customer`
--

INSERT INTO `customer` (`cu_id`, `cu_address`, `cu_code`, `cu_company_title`, `cu_email`, `cu_mobile`, `cu_name`, `cu_password`, `cu_phone`, `cu_status`, `cu_surname`, `cu_tax_administration`, `cu_tax_number`) VALUES
(1, 'DDAAAADDDAA', 4601652326, 'Esnaf', 'ali@mail.com', '124241412421', 'Ali', '12345', '124141242', 1, 'Bilmem', 'ADDDDAADD', 121233223),
(2, 'ASDASDASDASDASD', 12312, 'KKT', 'sercan@mail.com', '0122133212', 'Sercan', '12345', '0222123312213', 2, 'Bilir', 'ADCBVD', 2214124),
(3, 'Beykoz', 243354, 'Kombi Teknisyeni', 'davut@mail.com', '1421421', 'Davut', 'ea241r1', '412421421421', 2, 'BULUT', 'Kadıköy', 121242),
(4, 'KADIKÖY', 1683263, 'Avukat', 'ferhat@mail.com', '13124421421', 'FERHAT', 'ıojıo1oır', '412412421', 2, 'YILDIRIM', 'KADIKÖY', 21421421),
(5, 'Kadıköy', 434343344, 'Gişe Memuru', 'ayse@mail.com', '0561233213', 'Ayşe', '421421421', '0212872300', 2, 'Çalışır', 'Kadıköy', 252778337),
(8, 'Kağıthane', 2543435, 'Uzman', 'mehmet@mail.com', '05405454', 'Mehmet', '231231', '03433043', 2, 'Has', 'Kağıthane', 435553113),
(9, 'aawfwfwfa', 786903181, 'wdwadwda', 'adat@mail.com', '9889239', 'dwadwa', '12345', '18387213', 2, 'dwawdadwa', 'effwfwfa', 121224);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `fis_no` int(11) NOT NULL,
  `order_size` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `customer_cu_id` int(11) DEFAULT NULL,
  `products_pro_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `orders`
--

INSERT INTO `orders` (`order_id`, `date`, `fis_no`, `order_size`, `status`, `total`, `customer_cu_id`, `products_pro_id`) VALUES
(13, '2021-08-30', 621196683, 4, 1, 1200000, 4, 3),
(14, '2021-08-30', 621196683, 2, 1, 288756, 4, 1),
(15, '2021-08-31', 621229530, 20, 1, 350000, 5, 10),
(16, '2021-09-03', 621229530, 14, 1, 25000, 5, 6),
(17, '2021-09-03', 661763537, 80, 1, 8000, 4, 7),
(18, '2021-09-03', 662458158, 50, 1, 25000, 3, 4),
(19, '2021-09-03', 662458158, 10, 1, 1200000, 3, 3),
(20, '2021-09-03', 666851560, 4, 2, 1752000, 4, 3),
(23, '2021-09-04', 764696156, 20, 1, 25000, 8, 4),
(24, '2021-09-04', 764900376, 100, 1, 25000, 2, 6);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `products`
--

CREATE TABLE `products` (
  `pro_id` int(11) NOT NULL,
  `pro_amount` int(11) NOT NULL,
  `pro_buying_price` int(11) NOT NULL,
  `pro_code` bigint(20) NOT NULL,
  `pro_detail` varchar(500) DEFAULT NULL,
  `pro_sale_price` int(11) NOT NULL,
  `pro_tax_status` int(11) NOT NULL,
  `pro_title` varchar(500) DEFAULT NULL,
  `pro_unit_status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `products`
--

INSERT INTO `products` (`pro_id`, `pro_amount`, `pro_buying_price`, `pro_code`, `pro_detail`, `pro_sale_price`, `pro_tax_status`, `pro_title`, `pro_unit_status`) VALUES
(1, 1234, 150, 643, 'Beyaz', 234, 2, 'Gömlek', 0),
(3, 150, 8000, 65615, 'Beyaz', 12000, 3, 'Buzdolabı', 0),
(4, 100, 200, 2444, 'Turuncu', 250, 1, 'Gömlek', 0),
(6, 1000, 15, 5814, '1. Hamur', 25, 1, 'Kağıt', 1),
(7, 2000, 2, 1257, 'Siyah', 4, 2, 'Silgi', 0),
(8, 1000, 5, 222581, '0.7', 9, 1, 'Kalem', 0),
(10, 10000, 15, 14885, 'Mavi', 35, 2, 'Kahve Fincanı', 0),
(12, 1000, 4, 686204167, '2L', 14, 3, 'Yağ Şişesi', 4),
(15, 20000, 5, 764514289, 'Tuzsuz', 15, 1, 'Çorum Leblebisi', 1),
(16, 1000, 24, 767009971, 'Bitkisel içerik', 66, 3, 'Tebokan', 0),
(17, 1000, 1, 768449208, '0.7', 3, 2, 'Kalem ucu', 0),
(18, 1000, 8, 769271346, '0.7', 15, 1, 'Uçlu Kalem', 0),
(20, 100, 800, 769865815, '2+1', 1500, 3, 'Ses Sistemi', 0);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `saverobject`
--

CREATE TABLE `saverobject` (
  `fis_obj_no` int(11) NOT NULL,
  `cu_obj_id` int(11) NOT NULL,
  `or_obj_id` int(11) NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `cashin`
--
ALTER TABLE `cashin`
  ADD PRIMARY KEY (`cash_id`),
  ADD KEY `FKr3xrrrg4qny0vs6risi6aqhxi` (`order_order_id`);

--
-- Tablo için indeksler `cashobject`
--
ALTER TABLE `cashobject`
  ADD PRIMARY KEY (`cu_id`);

--
-- Tablo için indeksler `cashout`
--
ALTER TABLE `cashout`
  ADD PRIMARY KEY (`cashOut_id`);

--
-- Tablo için indeksler `cporder`
--
ALTER TABLE `cporder`
  ADD PRIMARY KEY (`cu_id`);

--
-- Tablo için indeksler `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`cu_id`);

--
-- Tablo için indeksler `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `FK1ttru6okx4lydanww3hieorbh` (`customer_cu_id`),
  ADD KEY `FKiqfr4io1ob207m2nn4obepwu5` (`products_pro_id`);

--
-- Tablo için indeksler `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`pro_id`);

--
-- Tablo için indeksler `saverobject`
--
ALTER TABLE `saverobject`
  ADD PRIMARY KEY (`fis_obj_no`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Tablo için AUTO_INCREMENT değeri `cashin`
--
ALTER TABLE `cashin`
  MODIFY `cash_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Tablo için AUTO_INCREMENT değeri `cashout`
--
ALTER TABLE `cashout`
  MODIFY `cashOut_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Tablo için AUTO_INCREMENT değeri `cporder`
--
ALTER TABLE `cporder`
  MODIFY `cu_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Tablo için AUTO_INCREMENT değeri `customer`
--
ALTER TABLE `customer`
  MODIFY `cu_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Tablo için AUTO_INCREMENT değeri `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Tablo için AUTO_INCREMENT değeri `products`
--
ALTER TABLE `products`
  MODIFY `pro_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Dökümü yapılmış tablolar için kısıtlamalar
--

--
-- Tablo kısıtlamaları `cashin`
--
ALTER TABLE `cashin`
  ADD CONSTRAINT `FKr3xrrrg4qny0vs6risi6aqhxi` FOREIGN KEY (`order_order_id`) REFERENCES `orders` (`order_id`);

--
-- Tablo kısıtlamaları `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FK1ttru6okx4lydanww3hieorbh` FOREIGN KEY (`customer_cu_id`) REFERENCES `customer` (`cu_id`),
  ADD CONSTRAINT `FKiqfr4io1ob207m2nn4obepwu5` FOREIGN KEY (`products_pro_id`) REFERENCES `products` (`pro_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
