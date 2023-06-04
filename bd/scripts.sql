DROP DATABASE IF EXISTS SettingMusicoterapia;
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema SettingMusicoterapia
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `SettingMusicoterapia` DEFAULT CHARACTER SET utf8 ;
USE `SettingMusicoterapia` ;

-- -----------------------------------------------------
-- Table `SettingMusicoterapia`.`Pais`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SettingMusicoterapia`.`Pais` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SettingMusicoterapia`.`Estado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SettingMusicoterapia`.`Estado` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `uf` CHAR(2) NOT NULL,
  `idPais` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Estado_Pais1_idx` (`idPais` ASC) VISIBLE,
  CONSTRAINT `fk_Estado_Pais1`
    FOREIGN KEY (`idPais`)
    REFERENCES `SettingMusicoterapia`.`Pais` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SettingMusicoterapia`.`Cidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SettingMusicoterapia`.`Cidade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `cep` CHAR(8) NULL,
  `idEstado` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Cidade_Estado1_idx` (`idEstado` ASC) VISIBLE,
  CONSTRAINT `fk_Cidade_Estado1`
    FOREIGN KEY (`idEstado`)
    REFERENCES `SettingMusicoterapia`.`Estado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SettingMusicoterapia`.`Religiao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SettingMusicoterapia`.`Religiao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SettingMusicoterapia`.`Profissao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SettingMusicoterapia`.`Profissao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SettingMusicoterapia`.`Paciente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SettingMusicoterapia`.`Paciente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `apelido` VARCHAR(50) NULL,
  `dataAdmissao` DATE NULL,
  `dataNascimento` DATE NULL,
  `sexo` CHAR(1) NULL,
  `idCidadeNaturalidade` INT NULL,
  `idPaisNacionalidade` INT NULL,
  `idReligiao` INT NULL,
  `idProfissao` INT NULL,
  `email` VARCHAR(50) NULL,
  `sempreMoraramNoBrasil` TINYINT NULL,
  `paisMoramJuntos` TINYINT NULL,
  `observacoesAmbienteFamiliar` TINYTEXT NULL,
  `prematuro` TINYINT NULL,
  `semanasPrematuro` TINYINT NULL,
  `nascimentoComComplicacoes` TINYINT NULL,
  `observacoesNascimento` TINYTEXT NULL,
  `hospitalizado` TINYINT NULL,
  `semanasHospitalizado` TINYINT NULL,
  `idadeHospitalizado` TINYINT NULL,
  `observacoesHospitalizado` TINYTEXT NULL,
  `observacoesHistoricoMedico` MEDIUMTEXT NULL,
  `mesesComecouSentar` TINYINT NULL,
  `mesesComecouEngatinhar` TINYINT NULL,
  `mesesComecouFalar` TINYINT NULL,
  `idadeBanheiroSozinho` TINYINT NULL,
  `compreendeGestosInteracao` TINYINT NULL,
  `comunicaVerbalmente` CHAR(1) NULL,
  `jaFezMusicoterapia` TINYINT NULL,
  `musicoterapiaLocal` VARCHAR(50) NULL,
  `musicoterapiaTempo` TINYINT NULL,
  `jaTeveAulasMusica` TINYINT NULL,
  `aulasMusicaLocal` VARCHAR(50) NULL,
  `aulasMusicaTempo` TINYINT NULL,
  `canta` TINYINT NULL,
  `habitoOuvirMusica` TINYINT NULL,
  `ouvirMusicaLocal` VARCHAR(50) NULL,
  `ouvirMusicaQuando` VARCHAR(50) NULL,
  `musicasPreferidas` TINYTEXT NULL,
  `musicasOdiadas` TINYTEXT NULL,
  `interageOuvindo` TINYINT NULL,
  `interageCantando` TINYINT NULL,
  `interageDancando` TINYINT NULL,
  `interageTocando` TINYINT NULL,
  `interageComPais` TINYINT NULL,
  `interageComIrmaos` TINYINT NULL,
  `observacoesHistoricoMusical` TINYTEXT NULL,
  `observacoesHistoricoDesenvolvimento` TINYTEXT NULL,
  `enderecoRua` VARCHAR(50) NULL,
  `enderecoComplemento` VARCHAR(50) NULL,
  `enderecoBairro` VARCHAR(50) NULL,
  `enderecoNumero` VARCHAR(10) NULL,
  `enderecoCep` CHAR(8) NULL,
  `idEnderecoCidade` INT NULL,
  `inativo` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Paciente_Cidade1_idx` (`idCidadeNaturalidade` ASC) VISIBLE,
  INDEX `fk_Paciente_Pais1_idx` (`idPaisNacionalidade` ASC) VISIBLE,
  INDEX `fk_Paciente_Religiao1_idx` (`idReligiao` ASC) VISIBLE,
  INDEX `fk_Paciente_Profissao1_idx` (`idProfissao` ASC) VISIBLE,
  INDEX `fk_Paciente_Cidade2_idx` (`idEnderecoCidade` ASC) VISIBLE,
  CONSTRAINT `fk_Paciente_Cidade1`
    FOREIGN KEY (`idCidadeNaturalidade`)
    REFERENCES `SettingMusicoterapia`.`Cidade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Paciente_Pais1`
    FOREIGN KEY (`idPaisNacionalidade`)
    REFERENCES `SettingMusicoterapia`.`Pais` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Paciente_Religiao1`
    FOREIGN KEY (`idReligiao`)
    REFERENCES `SettingMusicoterapia`.`Religiao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Paciente_Profissao1`
    FOREIGN KEY (`idProfissao`)
    REFERENCES `SettingMusicoterapia`.`Profissao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Paciente_Cidade2`
    FOREIGN KEY (`idEnderecoCidade`)
    REFERENCES `SettingMusicoterapia`.`Cidade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SettingMusicoterapia`.`Escolaridade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SettingMusicoterapia`.`Escolaridade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SettingMusicoterapia`.`InstituicaoEnsino`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SettingMusicoterapia`.`InstituicaoEnsino` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SettingMusicoterapia`.`Curso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SettingMusicoterapia`.`Curso` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SettingMusicoterapia`.`Familiar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SettingMusicoterapia`.`Familiar` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `dataNascimento` DATE NULL,
  `cpf` CHAR(11) NULL,
  `rg` VARCHAR(14) NULL,
  `adotivo` TINYINT NULL,
  `idProfissao` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Familiar_Profissao1_idx` (`idProfissao` ASC) VISIBLE,
  CONSTRAINT `fk_Familiar_Profissao1`
    FOREIGN KEY (`idProfissao`)
    REFERENCES `SettingMusicoterapia`.`Profissao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SettingMusicoterapia`.`GrauParentesco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SettingMusicoterapia`.`GrauParentesco` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SettingMusicoterapia`.`TelefonePaciente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SettingMusicoterapia`.`TelefonePaciente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idPaciente` INT NOT NULL,
  `telefone` VARCHAR(11) NOT NULL,
  INDEX `fk_ContatoPaciente_Paciente1_idx` (`idPaciente` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_ContatoPaciente_Paciente1`
    FOREIGN KEY (`idPaciente`)
    REFERENCES `SettingMusicoterapia`.`Paciente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SettingMusicoterapia`.`Endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SettingMusicoterapia`.`Endereco` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rua` VARCHAR(50) NOT NULL,
  `bairro` VARCHAR(50) NOT NULL,
  `complemento` VARCHAR(50) NULL,
  `numero` SMALLINT NULL,
  `cep` VARCHAR(45) NULL,
  `idCidade` INT NOT NULL,
  PRIMARY KEY (`id`, `idCidade`),
  INDEX `fk_Endereco_Cidade1_idx` (`idCidade` ASC) VISIBLE,
  CONSTRAINT `fk_Endereco_Cidade1`
    FOREIGN KEY (`idCidade`)
    REFERENCES `SettingMusicoterapia`.`Cidade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SettingMusicoterapia`.`EnderecoPaciente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SettingMusicoterapia`.`EnderecoPaciente` (
  `idEndereco` INT NOT NULL,
  `idPaciente` INT NOT NULL,
  PRIMARY KEY (`idEndereco`, `idPaciente`),
  INDEX `fk_EnderecoPaciente_Paciente1_idx` (`idPaciente` ASC) VISIBLE,
  CONSTRAINT `fk_EnderecoPaciente_Endereco1`
    FOREIGN KEY (`idEndereco`)
    REFERENCES `SettingMusicoterapia`.`Endereco` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_EnderecoPaciente_Paciente1`
    FOREIGN KEY (`idPaciente`)
    REFERENCES `SettingMusicoterapia`.`Paciente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SettingMusicoterapia`.`EnderecoFamiliar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SettingMusicoterapia`.`EnderecoFamiliar` (
  `idEndereco` INT NOT NULL,
  `idFamiliar` INT NOT NULL,
  PRIMARY KEY (`idEndereco`, `idFamiliar`),
  INDEX `fk_EnderecoFamiliar_Familiar1_idx` (`idFamiliar` ASC) VISIBLE,
  CONSTRAINT `fk_EnderecoFamiliar_Endereco1`
    FOREIGN KEY (`idEndereco`)
    REFERENCES `SettingMusicoterapia`.`Endereco` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_EnderecoFamiliar_Familiar1`
    FOREIGN KEY (`idFamiliar`)
    REFERENCES `SettingMusicoterapia`.`Familiar` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SettingMusicoterapia`.`Terapia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SettingMusicoterapia`.`Terapia` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SettingMusicoterapia`.`Terapeuta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SettingMusicoterapia`.`Terapeuta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SettingMusicoterapia`.`TerapiaPaciente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SettingMusicoterapia`.`TerapiaPaciente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idPaciente` INT NOT NULL,
  `idTerapia` INT NOT NULL,
  `idTerapeuta` INT NOT NULL,
  `frequencia` SMALLINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_TerapiaPaciente_Paciente1_idx` (`idPaciente` ASC) VISIBLE,
  INDEX `fk_TerapiaPaciente_Terapia1_idx` (`idTerapia` ASC) VISIBLE,
  INDEX `fk_TerapiaPaciente_Terapeuta1_idx` (`idTerapeuta` ASC) VISIBLE,
  CONSTRAINT `fk_TerapiaPaciente_Paciente1`
    FOREIGN KEY (`idPaciente`)
    REFERENCES `SettingMusicoterapia`.`Paciente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TerapiaPaciente_Terapia1`
    FOREIGN KEY (`idTerapia`)
    REFERENCES `SettingMusicoterapia`.`Terapia` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TerapiaPaciente_Terapeuta1`
    FOREIGN KEY (`idTerapeuta`)
    REFERENCES `SettingMusicoterapia`.`Terapeuta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SettingMusicoterapia`.`HistoricoEscolarPaciente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SettingMusicoterapia`.`HistoricoEscolarPaciente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idPaciente` INT NOT NULL,
  `idCurso` INT NOT NULL,
  `idInstituicaoEnsino` INT NOT NULL,
  `idEscolaridade` INT NOT NULL,
  `estudando` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_HistoricoEscolarPaciente_Curso1_idx` (`idCurso` ASC) VISIBLE,
  INDEX `fk_HistoricoEscolarPaciente_InstituicaoEnsino1_idx` (`idInstituicaoEnsino` ASC) VISIBLE,
  INDEX `fk_HistoricoEscolarPaciente_Paciente1_idx` (`idPaciente` ASC) VISIBLE,
  INDEX `fk_HistoricoEscolarPaciente_Escolaridade1_idx` (`idEscolaridade` ASC) VISIBLE,
  CONSTRAINT `fk_HistoricoEscolarPaciente_Curso1`
    FOREIGN KEY (`idCurso`)
    REFERENCES `SettingMusicoterapia`.`Curso` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_HistoricoEscolarPaciente_InstituicaoEnsino1`
    FOREIGN KEY (`idInstituicaoEnsino`)
    REFERENCES `SettingMusicoterapia`.`InstituicaoEnsino` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_HistoricoEscolarPaciente_Paciente1`
    FOREIGN KEY (`idPaciente`)
    REFERENCES `SettingMusicoterapia`.`Paciente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_HistoricoEscolarPaciente_Escolaridade1`
    FOREIGN KEY (`idEscolaridade`)
    REFERENCES `SettingMusicoterapia`.`Escolaridade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SettingMusicoterapia`.`Medico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SettingMusicoterapia`.`Medico` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SettingMusicoterapia`.`Doenca`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SettingMusicoterapia`.`Doenca` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SettingMusicoterapia`.`DiagnosticoPaciente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SettingMusicoterapia`.`DiagnosticoPaciente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idPaciente` INT NOT NULL,
  `idDoenca` INT NOT NULL,
  `idMedicoPrincipal` INT NOT NULL,
  `idMedicoDiagnostico` INT NOT NULL,
  `data` DATE NULL,
  `idCidade` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_HistoricoMedicoPaciente_Medico1_idx` (`idMedicoPrincipal` ASC) VISIBLE,
  INDEX `fk_HistoricoMedicoPaciente_Medico2_idx` (`idMedicoDiagnostico` ASC) VISIBLE,
  INDEX `fk_DiagnosticoPaciente_Doenca1_idx` (`idDoenca` ASC) VISIBLE,
  INDEX `fk_DiagnosticoPaciente_Cidade1_idx` (`idCidade` ASC) VISIBLE,
  INDEX `fk_DiagnosticoPaciente_Paciente1_idx` (`idPaciente` ASC) VISIBLE,
  CONSTRAINT `fk_HistoricoMedicoPaciente_Medico1`
    FOREIGN KEY (`idMedicoPrincipal`)
    REFERENCES `SettingMusicoterapia`.`Medico` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_HistoricoMedicoPaciente_Medico2`
    FOREIGN KEY (`idMedicoDiagnostico`)
    REFERENCES `SettingMusicoterapia`.`Medico` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DiagnosticoPaciente_Doenca1`
    FOREIGN KEY (`idDoenca`)
    REFERENCES `SettingMusicoterapia`.`Doenca` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DiagnosticoPaciente_Cidade1`
    FOREIGN KEY (`idCidade`)
    REFERENCES `SettingMusicoterapia`.`Cidade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DiagnosticoPaciente_Paciente1`
    FOREIGN KEY (`idPaciente`)
    REFERENCES `SettingMusicoterapia`.`Paciente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SettingMusicoterapia`.`Medicamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SettingMusicoterapia`.`Medicamento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SettingMusicoterapia`.`MedicamentoPaciente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SettingMusicoterapia`.`MedicamentoPaciente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idPaciente` INT NOT NULL,
  `idMedicamento` INT NOT NULL,
  `dosagem` DECIMAL(10,2) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_MedicamentoPaciente_Medicamento1_idx` (`idMedicamento` ASC) VISIBLE,
  INDEX `fk_MedicamentoPaciente_Paciente1_idx` (`idPaciente` ASC) VISIBLE,
  CONSTRAINT `fk_MedicamentoPaciente_Medicamento1`
    FOREIGN KEY (`idMedicamento`)
    REFERENCES `SettingMusicoterapia`.`Medicamento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MedicamentoPaciente_Paciente1`
    FOREIGN KEY (`idPaciente`)
    REFERENCES `SettingMusicoterapia`.`Paciente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SettingMusicoterapia`.`InstrumentoMusical`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SettingMusicoterapia`.`InstrumentoMusical` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SettingMusicoterapia`.`InstrumentosPaciente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SettingMusicoterapia`.`InstrumentosPaciente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idPaciente` INT NOT NULL,
  `idInstrumentoMusical` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_InstrumentosPaciente_InstrumentoMusical1_idx` (`idInstrumentoMusical` ASC) VISIBLE,
  INDEX `fk_InstrumentosPaciente_Paciente1_idx` (`idPaciente` ASC) VISIBLE,
  CONSTRAINT `fk_InstrumentosPaciente_InstrumentoMusical1`
    FOREIGN KEY (`idInstrumentoMusical`)
    REFERENCES `SettingMusicoterapia`.`InstrumentoMusical` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_InstrumentosPaciente_Paciente1`
    FOREIGN KEY (`idPaciente`)
    REFERENCES `SettingMusicoterapia`.`Paciente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SettingMusicoterapia`.`FamiliarPaciente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SettingMusicoterapia`.`FamiliarPaciente` (
  `idPaciente` INT NOT NULL,
  `idFamiliar` INT NOT NULL,
  `idGrauParentesco` INT NOT NULL,
  `moraComPaciente` TINYINT NULL,
  `respondePelaMusicoterapia` TINYINT NULL,
  PRIMARY KEY (`idPaciente`, `idFamiliar`),
  INDEX `fk_Paciente_has_Familiar_Familiar1_idx` (`idFamiliar` ASC) VISIBLE,
  INDEX `fk_Paciente_has_Familiar_Paciente1_idx` (`idPaciente` ASC) VISIBLE,
  INDEX `fk_FamiliarPaciente_GrauParentesco1_idx` (`idGrauParentesco` ASC) VISIBLE,
  CONSTRAINT `fk_Paciente_has_Familiar_Paciente1`
    FOREIGN KEY (`idPaciente`)
    REFERENCES `SettingMusicoterapia`.`Paciente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Paciente_has_Familiar_Familiar1`
    FOREIGN KEY (`idFamiliar`)
    REFERENCES `SettingMusicoterapia`.`Familiar` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_FamiliarPaciente_GrauParentesco1`
    FOREIGN KEY (`idGrauParentesco`)
    REFERENCES `SettingMusicoterapia`.`GrauParentesco` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SettingMusicoterapia`.`Profissional`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SettingMusicoterapia`.`Profissional` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `idProfissao` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Profissional_Profissao1_idx` (`idProfissao` ASC) VISIBLE,
  CONSTRAINT `fk_Profissional_Profissao1`
    FOREIGN KEY (`idProfissao`)
    REFERENCES `SettingMusicoterapia`.`Profissao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SettingMusicoterapia`.`Convenio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SettingMusicoterapia`.`Convenio` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SettingMusicoterapia`.`Tratamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SettingMusicoterapia`.`Tratamento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idPaciente` INT NOT NULL,
  `idProfissional` INT NOT NULL,
  `inicioVigencia` DATE NOT NULL,
  `finalVigencia` DATE NULL,
  `horarioSessao` TIME NOT NULL,
  `duracaoMinutosSessao` INT NOT NULL,
  `frequenciaSessaoDias` INT NOT NULL,
  `idConvenio` INT NULL,
  `valor` DECIMAL(15,2) NOT NULL,
  `observacoes` TINYTEXT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Tratamento_Convenio1_idx` (`idConvenio` ASC) VISIBLE,
  INDEX `fk_Tratamento_Profissional1_idx` (`idProfissional` ASC) VISIBLE,
  INDEX `fk_Tratamento_Paciente1_idx` (`idPaciente` ASC) VISIBLE,
  CONSTRAINT `fk_Tratamento_Convenio1`
    FOREIGN KEY (`idConvenio`)
    REFERENCES `SettingMusicoterapia`.`Convenio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Tratamento_Profissional1`
    FOREIGN KEY (`idProfissional`)
    REFERENCES `SettingMusicoterapia`.`Profissional` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Tratamento_Paciente1`
    FOREIGN KEY (`idPaciente`)
    REFERENCES `SettingMusicoterapia`.`Paciente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SettingMusicoterapia`.`Consulta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SettingMusicoterapia`.`Consulta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idTratamento` INT NOT NULL,
  `data` DATE NOT NULL,
  `horarioInicio` TIME NOT NULL,
  `horarioTermino` TIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Consulta_Tratamento1_idx` (`idTratamento` ASC) VISIBLE,
  CONSTRAINT `fk_Consulta_Tratamento1`
    FOREIGN KEY (`idTratamento`)
    REFERENCES `SettingMusicoterapia`.`Tratamento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



INSERT INTO Pais VALUES (1, 'Brasil');
INSERT INTO Estado VALUES (1, 'Rio Grande do Sul', 'RS', 1);
INSERT INTO Cidade(id, nome, idEstado, cep) VALUES (1, 'Erechim', 1, '99700000');

INSERT INTO Profissao VALUES (1, 'Terapeuta');

INSERT INTO GrauParentesco VALUES
	(1, 'Pai/Mãe'),
    (2, 'Tio/Tia'),
    (3, 'Avô/Avó'),
    (4, 'Padrasto/Madrasta');
