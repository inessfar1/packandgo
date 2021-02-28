<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210228125044 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE agence (id INT AUTO_INCREMENT NOT NULL, nom VARCHAR(255) NOT NULL, pays VARCHAR(255) NOT NULL, ville VARCHAR(255) NOT NULL, adresse VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL, tel VARCHAR(255) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE chauffeur (id_chauffeur INT AUTO_INCREMENT NOT NULL, nom VARCHAR(255) DEFAULT NULL, prenom VARCHAR(255) DEFAULT NULL, date_d_n DATE DEFAULT NULL, PRIMARY KEY(id_chauffeur)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE moydetran (id_moy_trans INT AUTO_INCREMENT NOT NULL, type VARCHAR(255) DEFAULT NULL, matricule VARCHAR(255) DEFAULT NULL, disponibilite INT DEFAULT NULL, PRIMARY KEY(id_moy_trans)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE plan (id INT AUTO_INCREMENT NOT NULL, agence_id INT NOT NULL, image VARCHAR(255) NOT NULL, sujet VARCHAR(255) NOT NULL, description VARCHAR(255) NOT NULL, prix VARCHAR(255) NOT NULL, date DATETIME NOT NULL, INDEX IDX_DD5A5B7DD725330D (agence_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE trajet (id_trajet INT AUTO_INCREMENT NOT NULL, id_moy_trans INT DEFAULT NULL, date_depart DATE DEFAULT NULL, date_arrive DATE DEFAULT NULL, pt_depart VARCHAR(255) DEFAULT NULL, pt_arrive VARCHAR(255) DEFAULT NULL, id_chauffeur INT DEFAULT NULL, prix DOUBLE PRECISION DEFAULT NULL, INDEX FN_key (id_chauffeur), INDEX FN_keymoy (id_moy_trans), PRIMARY KEY(id_trajet)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE plan ADD CONSTRAINT FK_DD5A5B7DD725330D FOREIGN KEY (agence_id) REFERENCES agence (id)');
        $this->addSql('ALTER TABLE trajet ADD CONSTRAINT FK_2B5BA98CF0F0BA17 FOREIGN KEY (id_moy_trans) REFERENCES moydetran (id_moy_trans)');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE plan DROP FOREIGN KEY FK_DD5A5B7DD725330D');
        $this->addSql('ALTER TABLE trajet DROP FOREIGN KEY FK_2B5BA98CF0F0BA17');
        $this->addSql('DROP TABLE agence');
        $this->addSql('DROP TABLE chauffeur');
        $this->addSql('DROP TABLE moydetran');
        $this->addSql('DROP TABLE plan');
        $this->addSql('DROP TABLE trajet');
    }
}
