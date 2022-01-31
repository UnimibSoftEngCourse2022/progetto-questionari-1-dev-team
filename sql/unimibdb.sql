-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Creato il: Gen 31, 2022 alle 15:18
-- Versione del server: 10.3.31-MariaDB-0+deb10u1
-- Versione PHP: 7.3.31-1~deb10u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `unimibdb`
--
CREATE DATABASE IF NOT EXISTS `unimibdb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `unimibdb`;

-- --------------------------------------------------------

--
-- Struttura della tabella `Answer`
--

CREATE TABLE `Answer` (
  `id` int(11) NOT NULL,
  `text` varchar(255) DEFAULT NULL,
  `question_id` int(11) NOT NULL,
  `survey_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `Category`
--

CREATE TABLE `Category` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `CloseEndedAnswer`
--

CREATE TABLE `CloseEndedAnswer` (
  `id` int(11) NOT NULL,
  `text` varchar(255) DEFAULT NULL,
  `question_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `Question`
--

CREATE TABLE `Question` (
  `id` int(11) NOT NULL,
  `questionType` int(11) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `urlImage` varchar(255) DEFAULT NULL,
  `category_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `Survey`
--

CREATE TABLE `Survey` (
  `id` int(11) NOT NULL,
  `creationDate` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `SurveyQuestions`
--

CREATE TABLE `SurveyQuestions` (
  `id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `survey_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `User`
--

CREATE TABLE `User` (
  `id` int(11) NOT NULL,
  `compilationId` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `answer_closeendedanswer`
--

CREATE TABLE `answer_closeendedanswer` (
  `closeendedanswer_id` int(11) NOT NULL,
  `answer_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `Answer`
--
ALTER TABLE `Answer`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKfiomvt17psxodcis3d8nmopx8` (`question_id`),
  ADD KEY `FK622t5h9us1g3r7n9yayaso3bj` (`survey_id`),
  ADD KEY `FKjymrc2vabj23lc063hmrnnbni` (`user_id`);

--
-- Indici per le tabelle `Category`
--
ALTER TABLE `Category`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `CloseEndedAnswer`
--
ALTER TABLE `CloseEndedAnswer`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK8lvwl69t0ek71tn2tdq3mbblj` (`question_id`);

--
-- Indici per le tabelle `Question`
--
ALTER TABLE `Question`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKcfk2lqxngafrq0vyrjemeiu4r` (`category_id`),
  ADD KEY `FKqw0ti4wleodsoox6jci37j8y1` (`user_id`);

--
-- Indici per le tabelle `Survey`
--
ALTER TABLE `Survey`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKoy7lq7sdr3huu5fo9vibu7x5` (`user_id`);

--
-- Indici per le tabelle `SurveyQuestions`
--
ALTER TABLE `SurveyQuestions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK2pttrpb29itnbahny2jhr1o6o` (`question_id`),
  ADD KEY `FKhdj9eukojj7rcnwhkjb1lnr87` (`survey_id`);

--
-- Indici per le tabelle `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_frkhg7fbmhux1pufrk552pivb` (`compilationId`);

--
-- Indici per le tabelle `answer_closeendedanswer`
--
ALTER TABLE `answer_closeendedanswer`
  ADD PRIMARY KEY (`answer_id`,`closeendedanswer_id`),
  ADD KEY `FKgf4oqpc5qwfpgpoax638iwfse` (`closeendedanswer_id`);

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `Answer`
--
ALTER TABLE `Answer`
  ADD CONSTRAINT `FK622t5h9us1g3r7n9yayaso3bj` FOREIGN KEY (`survey_id`) REFERENCES `Survey` (`id`),
  ADD CONSTRAINT `FKfiomvt17psxodcis3d8nmopx8` FOREIGN KEY (`question_id`) REFERENCES `Question` (`id`),
  ADD CONSTRAINT `FKjymrc2vabj23lc063hmrnnbni` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`);

--
-- Limiti per la tabella `CloseEndedAnswer`
--
ALTER TABLE `CloseEndedAnswer`
  ADD CONSTRAINT `FK8lvwl69t0ek71tn2tdq3mbblj` FOREIGN KEY (`question_id`) REFERENCES `Question` (`id`);

--
-- Limiti per la tabella `Question`
--
ALTER TABLE `Question`
  ADD CONSTRAINT `FKcfk2lqxngafrq0vyrjemeiu4r` FOREIGN KEY (`category_id`) REFERENCES `Category` (`id`),
  ADD CONSTRAINT `FKqw0ti4wleodsoox6jci37j8y1` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`);

--
-- Limiti per la tabella `Survey`
--
ALTER TABLE `Survey`
  ADD CONSTRAINT `FKoy7lq7sdr3huu5fo9vibu7x5` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`);

--
-- Limiti per la tabella `SurveyQuestions`
--
ALTER TABLE `SurveyQuestions`
  ADD CONSTRAINT `FK2pttrpb29itnbahny2jhr1o6o` FOREIGN KEY (`question_id`) REFERENCES `Question` (`id`),
  ADD CONSTRAINT `FKhdj9eukojj7rcnwhkjb1lnr87` FOREIGN KEY (`survey_id`) REFERENCES `Survey` (`id`);

--
-- Limiti per la tabella `answer_closeendedanswer`
--
ALTER TABLE `answer_closeendedanswer`
  ADD CONSTRAINT `FKgf4oqpc5qwfpgpoax638iwfse` FOREIGN KEY (`closeendedanswer_id`) REFERENCES `CloseEndedAnswer` (`id`),
  ADD CONSTRAINT `FKqj7knskrxorgm3y1fw0w28gxm` FOREIGN KEY (`answer_id`) REFERENCES `Answer` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
