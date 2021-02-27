<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210227150432 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE chauffeur ADD disponibilite INT DEFAULT NULL');
        $this->addSql('ALTER TABLE trajet DROP FOREIGN KEY FN_keymoy');
        $this->addSql('ALTER TABLE trajet CHANGE id_trajet id_trajet INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE trajet ADD CONSTRAINT FK_2B5BA98CF0F0BA17 FOREIGN KEY (id_moy_trans) REFERENCES moydetran (id_moy_trans)');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE chauffeur DROP disponibilite');
        $this->addSql('ALTER TABLE trajet DROP FOREIGN KEY FK_2B5BA98CF0F0BA17');
        $this->addSql('ALTER TABLE trajet CHANGE id_trajet id_trajet INT NOT NULL');
        $this->addSql('ALTER TABLE trajet ADD CONSTRAINT FN_keymoy FOREIGN KEY (id_moy_trans) REFERENCES moydetran (id_moy_trans) ON UPDATE CASCADE ON DELETE CASCADE');
    }
}
