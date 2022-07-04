CREATE DATABASE bd_moveis;
USE bd_moveis;

CREATE TABLE `cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `telefone` varchar(65) COLLATE utf8_unicode_ci NOT NULL,
  `nome` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `senha` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `encomenda` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data` datetime NOT NULL,
  `data_prevista` datetime NOT NULL,
  `data_entrega` datetime DEFAULT NULL,
  `data_vencimento` datetime DEFAULT NULL,
  `data_pagamento` datetime DEFAULT NULL,
  `data_prontidao` datetime DEFAULT NULL,
  `id_cliente` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_encomenda__cliente_id` (`id_cliente`),
  CONSTRAINT `fk_encomenda__cliente_id` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `linha_movel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `linha_nome` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `linha_descricao` varchar(300) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `movel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `modelo` varchar(65) COLLATE utf8_unicode_ci NOT NULL,
  `descricao` varchar(300) COLLATE utf8_unicode_ci NOT NULL,
  `preco` double NOT NULL,
  `id_linha_movel` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_movel__id_linha_movel` (`id_linha_movel`),
  CONSTRAINT `fk_movel__id_linha_movel` FOREIGN KEY (`id_linha_movel`) REFERENCES `linha_movel` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `item_encomenda` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_encomenda` int(11) NOT NULL,
  `id_movel` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_item_encomenda__movel_id` (`id_movel`),
  KEY `fk_item_encomenda__encomenda_id` (`id_encomenda`),
  CONSTRAINT `fk_item_encomenda__encomenda_id` FOREIGN KEY (`id_encomenda`) REFERENCES `encomenda` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_item_encomenda__movel_id` FOREIGN KEY (`id_movel`) REFERENCES `movel` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

