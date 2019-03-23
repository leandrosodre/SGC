-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: sgc
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `agenda`
--

DROP TABLE IF EXISTS `agenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `agenda` (
  `id_agenda` bigint(20) NOT NULL,
  `data_prevista` datetime DEFAULT NULL,
  `resultado` char(1) DEFAULT NULL,
  `paciente_id_paciente` bigint(20) DEFAULT NULL,
  `usuario_medico_id_usuario` bigint(20) DEFAULT NULL,
  `consulta_id_consulta` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_agenda`),
  KEY `FKhhh5npcbrn4jqw32qpuowr706` (`consulta_id_consulta`),
  KEY `FKnvs1jfb5lcosnsl3jol98ge8q` (`paciente_id_paciente`),
  KEY `FK1wf20iqbba20rahifavsxvykj` (`usuario_medico_id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agenda`
--

LOCK TABLES `agenda` WRITE;
/*!40000 ALTER TABLE `agenda` DISABLE KEYS */;
INSERT INTO `agenda` VALUES (1,'2020-08-31 08:30:00','F',31,35,135),(51,'2018-09-07 22:10:00','F',31,35,272),(53,'2018-09-06 10:00:00','F',30,35,158),(54,'2019-09-11 22:11:00','F',70,35,284),(88,'2019-08-10 20:20:00','F',31,35,283),(93,'2019-03-09 10:00:00','F',30,35,273),(94,'2020-01-01 06:22:00','F',70,35,166),(136,'2000-02-15 11:50:00','F',30,35,163),(167,'2030-10-10 11:50:00','F',30,108,179),(168,'2020-10-10 11:50:00','F',70,108,275),(169,'2011-02-10 05:00:00','F',31,108,172),(173,'2019-03-18 20:20:00','F',30,35,276),(237,'2019-03-21 09:00:00','F',30,35,277),(239,'2019-03-21 17:00:00','F',70,35,281),(241,'2019-03-21 18:00:00','F',31,35,282),(243,'2019-03-21 15:00:00','F',30,35,280),(245,'2019-03-21 14:00:00','F',30,35,279),(247,'2019-03-21 11:00:00','F',31,35,278),(286,'2019-03-25 08:30:00','F',30,35,287),(290,'2019-03-25 09:00:00',NULL,30,35,NULL);
/*!40000 ALTER TABLE `agenda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consulta`
--

DROP TABLE IF EXISTS `consulta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `consulta` (
  `id_consulta` bigint(20) NOT NULL,
  `recomendacoes` varchar(255) DEFAULT NULL,
  `sintomas` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_consulta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consulta`
--

LOCK TABLES `consulta` WRITE;
/*!40000 ALTER TABLE `consulta` DISABLE KEYS */;
INSERT INTO `consulta` VALUES (133,'vcsdg','zgvsdga'),(134,'12341','astawetasetawt'),(135,'sersfsdferafd','etsetsetse'),(139,'wqefsdfqf','sadfasvfg2'),(158,'FDSADAFSAFSDF','aDSFDSAFFADSAFDS'),(161,'asdf','asdf'),(162,'123','123'),(163,'',''),(166,'123','aaa'),(172,'asdfas','dsfasdf'),(179,'sersrsertser','stetsertserset'),(272,'SADFSAFDSADFA','ASDFSAF'),(273,'SADFSADFSADFSADFASFD','ASDFSADFSAFD'),(275,'213421342341234','213421342134'),(276,'SDFSADFSADFSAFD','ASDFSA'),(277,'FSADFSADFSADFASDF','ASDFSADFSAD'),(278,'DFASDFSADFSADF','SADFSA'),(279,'FASDFSADFSADF','ASDFSADFSADFSADF'),(280,'FASDFSADFASDF','SADFASDFSADFASD'),(281,'ASDFASFDASDF','SADFSADFASDF'),(282,'DFSADFASFDSADF','SADFSADFSA'),(283,'SDFASDFASDF','SADFASFDA'),(284,'SADFASDFASDF','ASDFSADF'),(287,'fdsafdasdf','sdfaasdfas');
/*!40000 ALTER TABLE `consulta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consulta_medicamentos`
--

DROP TABLE IF EXISTS `consulta_medicamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `consulta_medicamentos` (
  `consulta_id_consulta` bigint(20) NOT NULL,
  `medicamentos_id_medicamento` bigint(20) NOT NULL,
  UNIQUE KEY `UK_5asw21tn32nxtskva8h255av2` (`medicamentos_id_medicamento`),
  KEY `FK7chsxltk70rhftx10t3jykhmj` (`consulta_id_consulta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consulta_medicamentos`
--

LOCK TABLES `consulta_medicamentos` WRITE;
/*!40000 ALTER TABLE `consulta_medicamentos` DISABLE KEYS */;
/*!40000 ALTER TABLE `consulta_medicamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disponibilidade_horario`
--

DROP TABLE IF EXISTS `disponibilidade_horario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `disponibilidade_horario` (
  `id_dispo` bigint(20) NOT NULL,
  `dia_semana` int(11) NOT NULL,
  `disponivel` char(1) NOT NULL,
  `hora` int(11) NOT NULL,
  `minuto` int(11) NOT NULL,
  `usuario_id_usuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_dispo`),
  KEY `FKjetprwyc24c57jwqho68f2k05` (`usuario_id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disponibilidade_horario`
--

LOCK TABLES `disponibilidade_horario` WRITE;
/*!40000 ALTER TABLE `disponibilidade_horario` DISABLE KEYS */;
INSERT INTO `disponibilidade_horario` VALUES (251,2,'N',8,0,35),(252,2,'N',8,30,35),(253,2,'N',9,0,35),(254,2,'S',9,30,35),(255,2,'S',10,0,35),(256,2,'S',10,30,35),(257,2,'S',11,0,35),(258,2,'S',11,30,35),(358,3,'S',10,30,35),(357,3,'S',10,0,35);
/*!40000 ALTER TABLE `disponibilidade_horario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exame`
--

DROP TABLE IF EXISTS `exame`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `exame` (
  `id_exame` bigint(20) NOT NULL,
  `data_exame` datetime DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `resultado` varchar(255) DEFAULT NULL,
  `paciente_id_paciente` bigint(20) DEFAULT NULL,
  `tipo_exame_id_tipo_exame` bigint(20) DEFAULT NULL,
  `usuario_solicitante_id_usuario` bigint(20) DEFAULT NULL,
  `formato_arquivo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_exame`),
  KEY `FKch6cel6tecma3pn68x1oj5lqv` (`paciente_id_paciente`),
  KEY `FK4ppfu4ow73omp0i25eub3oj9h` (`tipo_exame_id_tipo_exame`),
  KEY `FK7td415ah59kmafn200ywkdhtk` (`usuario_solicitante_id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exame`
--

LOCK TABLES `exame` WRITE;
/*!40000 ALTER TABLE `exame` DISABLE KEYS */;
INSERT INTO `exame` VALUES (101,'2019-10-10 11:50:00','12','32',30,1,1,NULL),(102,'2020-11-11 12:50:00','111','2',70,3,1,NULL),(103,'2019-02-10 15:20:00','1','12',70,2,1,NULL),(104,'2019-03-08 17:30:00','sgsajkgsagsakjgksalgjsadlkgjaslfgjksalgkjsadlfkjaweojsdagkljsglkajsdfklsdjglsajgsadlkjfslgjsaklgjsadklgjfdklgjasdlkgjsadlgjsadlkgjsdlkgjaglkjsafgkljsagkljflkgjaskdçgjfkdlgjaslkgjalkgjkslgj','111',31,3,1,NULL),(105,'2019-03-09 20:20:00','dlshfsdjfhskjfhkjsafhkasjfhskaljdfhsakjdfhskjdfhsakdfhskadlfshkadjfhsakdfjhsadfhklsadhfksjdfhksajldfsalkdfsdaklfsdakfshkadjfhsdkfjhskdajfhsakdfjsahdkflhsadkjfsahdkfljsflkshdfkjsadhfklsahdfsa','AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA',30,1,1,NULL),(111,NULL,'Teste realizacao','123',NULL,NULL,1,NULL),(113,NULL,'Teste de Exame','Teste resultado exame',NULL,NULL,1,NULL),(114,NULL,'Teste','Teste123',NULL,NULL,1,NULL),(115,'2020-02-10 10:20:00','123','TESTE',30,4,1,NULL),(116,'2019-02-15 11:00:00','Teste Editar','Teste realizacao do exame',30,3,1,NULL),(118,'2020-01-20 20:20:00','12r4121','Testestsetsetset',31,1,1,NULL),(148,'2030-10-10 22:22:00','03121d6f51sd32f1sd356fg14sd65f14ws','1\'23145123512345',30,1,1,NULL),(180,'2010-10-10 11:50:00','sdlkfgjaslkfjsklfjsalkdfjka','etstset',30,3,1,NULL),(209,'2020-02-20 11:50:00','23sd1f562ad1sf56as1d56f14a','12451351241234',30,1,1,NULL),(210,'2010-10-10 20:10:00','bjkndfgshjkçcdvzfbjklcvxbzjklfbcdvxzhjkçklçcvxnbgklçxcgvbm,c n kcjxhblkxcbnjkxcdbklmçcdxbn jkxc bjklçxcdl gjklbx dobnciobxdlkbnxckuvbmy dxoikh fljkhnxc','132451235132451',31,1,35,NULL),(211,'2050-10-10 11:50:00','lkjgfklsdglkdfglidsfjg','Restultado nsdishr3',70,3,35,NULL),(213,'2050-10-10 11:50:00','123d2as1fa231dsf','123',31,4,35,NULL),(215,'2050-10-10 11:50:00','qw41412341\r\n','123',30,3,35,NULL),(221,'2020-10-10 10:02:00','wd1fgsag5as1g3asfgh\r\n','asdgasdgfas',30,3,196,NULL),(223,'2020-02-10 22:22:00','231qs635rf1a','sdfsdfgasdgasdgas',30,4,196,NULL),(225,'2010-10-12 22:00:00','Teste','REJSLKFOANELFNDAIUCAKEF',30,2,196,'.pdf'),(227,'2010-10-10 22:22:00','kçjlghsdfbhjkafgshjkafgs','dsfgasdgasdgasdfg',30,3,196,'.pdf'),(229,'2010-10-10 22:10:00','s21dfg564swad4f56sd4f6sd','sacvasdfscvsadf',30,1,196,'.pdf'),(231,'2020-02-20 11:20:00','4256464365465','234513512',30,2,196,'.pdf'),(233,'2020-10-10 20:00:00','232516546584gasdgf','dydrydrt',30,2,196,'.pdf'),(235,'2020-10-10 22:22:00','3251ds685fg4sd6fafsadfsadfsa',NULL,30,2,NULL,NULL),(288,'2020-10-10 22:22:00','sdfsad6g4sad',NULL,31,1,196,NULL);
/*!40000 ALTER TABLE `exame` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (359);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `horarios`
--

DROP TABLE IF EXISTS `horarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `horarios` (
  `id_horarios` bigint(20) NOT NULL,
  `dia_semana` int(11) NOT NULL,
  `hora_fim` int(11) NOT NULL,
  `hora_inicio` int(11) NOT NULL,
  `minuto_fim` int(11) NOT NULL,
  `minuto_inicio` int(11) NOT NULL,
  `usuario_id_usuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_horarios`),
  KEY `FK66ajeqkn9mg3cvcpcc51nr299` (`usuario_id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horarios`
--

LOCK TABLES `horarios` WRITE;
/*!40000 ALTER TABLE `horarios` DISABLE KEYS */;
INSERT INTO `horarios` VALUES (45,5,20,8,0,0,35),(47,6,2,1,0,0,35),(48,7,4,20,3,3,35),(49,2,21,20,40,12,35),(149,3,12,10,30,20,35),(150,3,10,20,30,0,35),(151,2,10,10,29,30,35),(174,4,12,8,0,0,35),(259,2,12,8,0,0,35),(338,3,11,10,0,0,35);
/*!40000 ALTER TABLE `horarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicamento`
--

DROP TABLE IF EXISTS `medicamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `medicamento` (
  `id_medicamento` bigint(20) NOT NULL,
  `nome_fabrica` varchar(255) DEFAULT NULL,
  `nome_fabricante` varchar(255) DEFAULT NULL,
  `nome_generico` varchar(255) DEFAULT NULL,
  `ativo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_medicamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicamento`
--

LOCK TABLES `medicamento` WRITE;
/*!40000 ALTER TABLE `medicamento` DISABLE KEYS */;
INSERT INTO `medicamento` VALUES (97,'22254','3','2','S'),(98,'45352','6','5','N'),(128,'111111','1','test','N');
/*!40000 ALTER TABLE `medicamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mensagem`
--

DROP TABLE IF EXISTS `mensagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `mensagem` (
  `id_mensagem` bigint(20) NOT NULL,
  `lida` char(1) NOT NULL,
  `texto_mensagem` varchar(255) DEFAULT NULL,
  `usuario_dest_id_usuario` bigint(20) DEFAULT NULL,
  `usuario_remet_id_usuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_mensagem`),
  KEY `FKcom7tw88h59mce2fuss1qv67v` (`usuario_dest_id_usuario`),
  KEY `FKgh0rrne39wffw3tw8xd2h7olw` (`usuario_remet_id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensagem`
--

LOCK TABLES `mensagem` WRITE;
/*!40000 ALTER TABLE `mensagem` DISABLE KEYS */;
INSERT INTO `mensagem` VALUES (195,'S','Teste',35,1),(201,'S','111111111',35,196),(202,'N','123',108,196),(203,'S','dadfasdf',196,108),(204,'S','123',35,196),(205,'S','123',35,196),(206,'S','123',35,196),(207,'N','sdfasf',108,196),(208,'N','asdfasd',108,196),(212,'N','O Exame numero 211 do paciente (Teste Delete)foi concluido e já pode ser verificado o resultado.',NULL,196),(214,'N','O Exame numero 213 do paciente (Teste4) foi concluido e já pode ser verificado o resultado.',NULL,196),(216,'S','O Exame numero 215 do paciente (Teste1) foi concluido e já pode ser verificado o resultado.',35,196),(217,'N','O Exame numero 118 do paciente (Teste4) foi concluido e já pode ser verificado o resultado.',1,196),(218,'N','O Exame numero 180 do paciente (Teste1) foi concluido e já pode ser verificado o resultado.',1,196),(219,'N','O Exame numero 209 do paciente (Teste1) foi concluido e já pode ser verificado o resultado.',1,196),(220,'S','O Exame numero 210 do paciente (Teste4) foi concluido e já pode ser verificado o resultado.',35,196),(222,'S','O Exame numero 221 do paciente (Teste1) foi concluido e já pode ser verificado o resultado.',196,196),(224,'S','O Exame numero 223 do paciente (Teste1) foi concluido e já pode ser verificado o resultado.',196,196),(226,'S','O Exame numero 225 do paciente (Teste1) foi concluido e já pode ser verificado o resultado.',196,196),(228,'S','O Exame numero 227 do paciente (Teste1) foi concluido e já pode ser verificado o resultado.',196,196),(230,'S','O Exame numero 229 do paciente (Teste1) foi concluido e já pode ser verificado o resultado.',196,196),(232,'S','O Exame numero 231 do paciente (Teste1) foi concluido e já pode ser verificado o resultado.',196,196),(234,'S','O Exame numero 233 do paciente (Teste1) foi concluido e já pode ser verificado o resultado.',196,196),(236,'S','Consulta marcada para o paciente null no dia Thu Mar 21 09:00:00 BRT 2019',35,196),(238,'S','Consulta marcada para o paciente null no dia Thu Mar 21 17:00:00 BRT 2019',35,196),(240,'S','Consulta marcada para o paciente null no dia Thu Mar 21 18:00:00 BRT 2019',35,196),(242,'S','Consulta marcada para o paciente null no dia Thu Mar 21 15:00:00 BRT 2019',35,196),(244,'S','Consulta marcada para o paciente Teste1 no dia Thu Mar 21 14:00:00 BRT 2019',35,196),(246,'S','Consulta marcada para o paciente Teste4 no dia 21/02/2019',35,196),(260,'N','Consulta marcada para o paciente Teste1 no dia 25/02/2019',35,196),(261,'N','Consulta marcada para o paciente Teste1 no dia 25/02/2019',35,196),(263,'N','Consulta marcada para o paciente Teste1 no dia 25/01/2010',35,196),(264,'N','Consulta marcada para o paciente Teste1 no dia 25/02/2019',35,196),(266,'N','Consulta marcada para o paciente Teste Delete no dia 25/02/2019',35,196),(268,'N','Consulta marcada para o paciente Teste4 no dia 25/02/2019',35,196),(285,'N','Consulta marcada para o paciente Teste1 no dia 25/02/2019',35,196),(289,'N','Consulta marcada para o paciente Teste1 no dia 25/02/2019',35,196);
/*!40000 ALTER TABLE `mensagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paciente`
--

DROP TABLE IF EXISTS `paciente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `paciente` (
  `id_paciente` bigint(20) NOT NULL,
  `bairro` varchar(255) DEFAULT NULL,
  `celular` varchar(255) DEFAULT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `cidade` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `cpf` varchar(255) DEFAULT NULL,
  `data_nascimento` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `estado_civil` int(11) NOT NULL,
  `fone_comercial` varchar(255) DEFAULT NULL,
  `nome_completo` varchar(255) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `sexo` char(1) NOT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_paciente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paciente`
--

LOCK TABLES `paciente` WRITE;
/*!40000 ALTER TABLE `paciente` DISABLE KEYS */;
INSERT INTO `paciente` VALUES (30,'Centro','(31) 98937-5837','12.325-353','Belo Horizonte','casa','520.215.120-50','1998-10-20 00:00:00','lerjeiwrji@ksnfd.com.br','Rua Principal','MG',5,'(31) 9285-9378','Teste1',123,'M','(32) 1390-1824'),(31,'Centro','(31) 98937-5837','12.325-353','Belo Horizonte','casa','102.312.301-20','1991-01-01 00:00:00','lerjeiwrji@ksnfd.com.br','Rua Principal','MG',1,'(31) 9285-9378','Teste4',123,'M','(32) 1390-1824'),(70,'beihao0es','(56) 48679-6496','30.535-380','Belo Horizonte','cas','123.487.851-87','1991-10-10 00:00:00','lsaemi@dsi.com.br','Rua Guatambu 100','kjfsdhfiq',1,'(56) 4567-4974','Teste Delete',100,'M','(23) 4687-4942');
/*!40000 ALTER TABLE `paciente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posologia`
--

DROP TABLE IF EXISTS `posologia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `posologia` (
  `id_posologia` bigint(20) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `qtd` int(11) NOT NULL,
  `medicamento_id_medicamento` bigint(20) DEFAULT NULL,
  `agenda_id_agenda` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_posologia`),
  KEY `FKonc8n6al4sbfn8ccpvk4dxi45` (`agenda_id_agenda`),
  KEY `FKda525q4jkw15307aayned2s2i` (`medicamento_id_medicamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posologia`
--

LOCK TABLES `posologia` WRITE;
/*!40000 ALTER TABLE `posologia` DISABLE KEYS */;
INSERT INTO `posologia` VALUES (127,'Teste',1,98,1),(129,'es',3,97,1),(131,'teste',2,128,1),(132,'teste',123,98,1),(153,'1234165324654',3,97,53),(155,'2314',1,98,53),(159,'123',3,98,94),(157,'315236136',123,98,53),(164,'121',123,98,94),(165,'13131',3,128,94),(170,'111',11,98,169),(171,'1242341',1234,128,169),(176,'sgsg',123,97,167),(177,'sdfa',3,128,167),(178,'werqw',21,98,167),(274,'ASDFSADSADFSADFSADFASDF',2,97,168);
/*!40000 ALTER TABLE `posologia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `role` (
  `nome_role` varchar(20) NOT NULL,
  PRIMARY KEY (`nome_role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES ('ROLE_ADMIN'),('ROLE_LAB'),('ROLE_MED'),('ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_usuarios`
--

DROP TABLE IF EXISTS `role_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `role_usuarios` (
  `role_nome_role` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `usuarios_id_usuario` bigint(20) NOT NULL,
  KEY `FKtb4t25jxq4gg5gajmh83h05nr` (`usuarios_id_usuario`),
  KEY `FK4021ish78hu63a0vuyhrqven0` (`role_nome_role`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_usuarios`
--

LOCK TABLES `role_usuarios` WRITE;
/*!40000 ALTER TABLE `role_usuarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_exame`
--

DROP TABLE IF EXISTS `tipo_exame`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `tipo_exame` (
  `id_tipo_exame` bigint(20) NOT NULL,
  `tipo_exame` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_exame`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_exame`
--

LOCK TABLES `tipo_exame` WRITE;
/*!40000 ALTER TABLE `tipo_exame` DISABLE KEYS */;
INSERT INTO `tipo_exame` VALUES (1,'Tipo 1'),(2,'Tipo 2'),(3,'Tipo 3'),(4,'Tipo 4');
/*!40000 ALTER TABLE `tipo_exame` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `usuario` (
  `id_usuario` bigint(20) NOT NULL,
  `data_inicio` datetime DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `nivel` int(11) NOT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `status` char(1) NOT NULL,
  `pessoa_id_pessoa` bigint(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome_usuario` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `FKdrv5jacr0jtocue1sculsvwvs` (`pessoa_id_pessoa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,NULL,'leandrey1',1,'$2a$10$2OzdP1P7x/6WjxJbIyT.5u7E5ZykAl2ayS/NZ4t8MPv6GkQGeBcLS','I',NULL,'leandro@augusto.com.br','leandrey1'),(187,NULL,'teste3',1,'$2a$10$omSwu8JdrlhNZoTqaEDOi.IR7ygpSJbKEAwjtgLyW9m4XSf3lQ.NG','I',NULL,'123','teste3'),(196,'2019-03-19 11:57:42','leandrey',1,'$2a$10$.H9qiNSpZGVt.92q8fZCxe06CK7DZpx9H/rTGg4/awv.RKyIyPePK','A',NULL,'leandroaugusto@ms.com.br','leandrey'),(197,NULL,'tes',3,'$2a$10$lTBT/KfwBaPJjs1CsE3/zO5M2qWBnhGIRKNZugpUGsyc8lXg6qax.','A',NULL,'123','teste'),(270,'2019-03-21 13:11:59','rcp',2,'$2a$10$.eyRDCTs3X4O7ukYLXYucegwnpLVMZwrNfLkQ8h2SgKYr2Quy2EWu','A',NULL,'rcp@gmail.com','Recepcao'),(271,'2019-03-21 13:12:18','lab',4,'$2a$10$JSVkDvuFth0MOh4q7i6sm.XhdGnK8Ma1Dv9Z0ieUzYzEv0TsHgaVS','A',NULL,'lab@lab.com.br','lab'),(35,NULL,'med',3,'$2a$10$d8el7RKYaXDsDnQCKSrilefSP7m7.j1IBwZ8dfpHrse5ctH0ER.ra','A',NULL,'medico@em.com.br','medico'),(108,NULL,'med1',3,'$2a$10$bHXQ6WwMtX5LcvTtxeL4jeueYHAeXoR/M/BGMsetmwKLOKUTZuxBS','A',NULL,'med@med.com.br','med');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_horarios`
--

DROP TABLE IF EXISTS `usuario_horarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `usuario_horarios` (
  `usuario_id_usuario` bigint(20) NOT NULL,
  `horarios_id_horarios` bigint(20) NOT NULL,
  UNIQUE KEY `UK_68p703ar9g3w97u2uq92nbc1o` (`horarios_id_horarios`),
  KEY `FKp22t3kgj4f2vfp52p46ky7sop` (`usuario_id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_horarios`
--

LOCK TABLES `usuario_horarios` WRITE;
/*!40000 ALTER TABLE `usuario_horarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario_horarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_roles`
--

DROP TABLE IF EXISTS `usuario_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `usuario_roles` (
  `usuario_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  KEY `FK3t77lxrs76nthhni13ctc7dlj` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_roles`
--

LOCK TABLES `usuario_roles` WRITE;
/*!40000 ALTER TABLE `usuario_roles` DISABLE KEYS */;
INSERT INTO `usuario_roles` VALUES ('cadu','ROLE_USER'),('teste2','ROLE_MED'),('teste3','ROLE_ADMIN'),('125235','ROLE_MED'),('leandrey','ROLE_ADMIN'),('med','ROLE_MED'),('med1','ROLE_MED'),('123','ROLE_ADMIN'),('leandrey','ROLE_ADMIN'),('tes','ROLE_MED'),('rcp','ROLE_USER'),('lab','ROLE_LAB');
/*!40000 ALTER TABLE `usuario_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-23 12:51:11
