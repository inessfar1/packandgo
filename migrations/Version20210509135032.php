<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210509135032 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE agence DROP update_at');
        $this->addSql('ALTER TABLE plan DROP update_at, CHANGE date date DATE NOT NULL, CHANGE rec rec INT DEFAULT NULL, CHANGE nbr nbr INT DEFAULT NULL');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE agence ADD update_at DATETIME DEFAULT NULL');
        $this->addSql('ALTER TABLE plan ADD update_at DATETIME DEFAULT NULL, CHANGE date date DATE DEFAULT NULL, CHANGE rec rec INT DEFAULT 0, CHANGE nbr nbr INT DEFAULT 5');
    }
}
