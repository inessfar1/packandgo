<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210424165103 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('DROP TABLE chambre_res_chambre');
        $this->addSql('DROP TABLE chambre_res_utilisateur');
        $this->addSql('DROP TABLE plan_res_plan');
        $this->addSql('DROP TABLE plan_res_utilisateur');
        $this->addSql('ALTER TABLE chambre_res ADD user_id INT DEFAULT NULL, ADD chambre_id INT DEFAULT NULL, CHANGE date_deb date_deb DATE NOT NULL, CHANGE date_fin date_fin DATE NOT NULL');
        $this->addSql('ALTER TABLE chambre_res ADD CONSTRAINT FK_7EFB3049A76ED395 FOREIGN KEY (user_id) REFERENCES utilisateur (id)');
        $this->addSql('ALTER TABLE chambre_res ADD CONSTRAINT FK_7EFB30499B177F54 FOREIGN KEY (chambre_id) REFERENCES chambre (id)');
        $this->addSql('CREATE UNIQUE INDEX UNIQ_7EFB3049A76ED395 ON chambre_res (user_id)');
        $this->addSql('CREATE UNIQUE INDEX UNIQ_7EFB30499B177F54 ON chambre_res (chambre_id)');
        $this->addSql('ALTER TABLE plan_res ADD user_id INT DEFAULT NULL, ADD plan_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE plan_res ADD CONSTRAINT FK_75536115A76ED395 FOREIGN KEY (user_id) REFERENCES utilisateur (id)');
        $this->addSql('ALTER TABLE plan_res ADD CONSTRAINT FK_75536115E899029B FOREIGN KEY (plan_id) REFERENCES plan (id)');
        $this->addSql('CREATE UNIQUE INDEX UNIQ_75536115A76ED395 ON plan_res (user_id)');
        $this->addSql('CREATE UNIQUE INDEX UNIQ_75536115E899029B ON plan_res (plan_id)');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE chambre_res_chambre (chambre_res_id INT NOT NULL, chambre_id INT NOT NULL, INDEX IDX_1647CE629B177F54 (chambre_id), INDEX IDX_1647CE62EC236E66 (chambre_res_id), PRIMARY KEY(chambre_res_id, chambre_id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE chambre_res_utilisateur (chambre_res_id INT NOT NULL, utilisateur_id INT NOT NULL, INDEX IDX_16CC7819EC236E66 (chambre_res_id), INDEX IDX_16CC7819FB88E14F (utilisateur_id), PRIMARY KEY(chambre_res_id, utilisateur_id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE plan_res_plan (plan_res_id INT NOT NULL, plan_id INT NOT NULL, INDEX IDX_9757BEA8E899029B (plan_id), INDEX IDX_9757BEA8F228FE76 (plan_res_id), PRIMARY KEY(plan_res_id, plan_id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE plan_res_utilisateur (plan_res_id INT NOT NULL, utilisateur_id INT NOT NULL, INDEX IDX_AE57B988F228FE76 (plan_res_id), INDEX IDX_AE57B988FB88E14F (utilisateur_id), PRIMARY KEY(plan_res_id, utilisateur_id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('ALTER TABLE chambre_res_chambre ADD CONSTRAINT FK_1647CE629B177F54 FOREIGN KEY (chambre_id) REFERENCES chambre (id) ON UPDATE NO ACTION ON DELETE CASCADE');
        $this->addSql('ALTER TABLE chambre_res_chambre ADD CONSTRAINT FK_1647CE62EC236E66 FOREIGN KEY (chambre_res_id) REFERENCES chambre_res (id) ON UPDATE NO ACTION ON DELETE CASCADE');
        $this->addSql('ALTER TABLE chambre_res_utilisateur ADD CONSTRAINT FK_16CC7819EC236E66 FOREIGN KEY (chambre_res_id) REFERENCES chambre_res (id) ON UPDATE NO ACTION ON DELETE CASCADE');
        $this->addSql('ALTER TABLE chambre_res_utilisateur ADD CONSTRAINT FK_16CC7819FB88E14F FOREIGN KEY (utilisateur_id) REFERENCES utilisateur (id) ON UPDATE NO ACTION ON DELETE CASCADE');
        $this->addSql('ALTER TABLE plan_res_plan ADD CONSTRAINT FK_9757BEA8E899029B FOREIGN KEY (plan_id) REFERENCES plan (id) ON UPDATE NO ACTION ON DELETE CASCADE');
        $this->addSql('ALTER TABLE plan_res_plan ADD CONSTRAINT FK_9757BEA8F228FE76 FOREIGN KEY (plan_res_id) REFERENCES plan_res (id) ON UPDATE NO ACTION ON DELETE CASCADE');
        $this->addSql('ALTER TABLE plan_res_utilisateur ADD CONSTRAINT FK_AE57B988F228FE76 FOREIGN KEY (plan_res_id) REFERENCES plan_res (id) ON UPDATE NO ACTION ON DELETE CASCADE');
        $this->addSql('ALTER TABLE plan_res_utilisateur ADD CONSTRAINT FK_AE57B988FB88E14F FOREIGN KEY (utilisateur_id) REFERENCES utilisateur (id) ON UPDATE NO ACTION ON DELETE CASCADE');
        $this->addSql('ALTER TABLE chambre_res DROP FOREIGN KEY FK_7EFB3049A76ED395');
        $this->addSql('ALTER TABLE chambre_res DROP FOREIGN KEY FK_7EFB30499B177F54');
        $this->addSql('DROP INDEX UNIQ_7EFB3049A76ED395 ON chambre_res');
        $this->addSql('DROP INDEX UNIQ_7EFB30499B177F54 ON chambre_res');
        $this->addSql('ALTER TABLE chambre_res DROP user_id, DROP chambre_id, CHANGE date_deb date_deb DATE DEFAULT NULL, CHANGE date_fin date_fin DATE DEFAULT NULL');
        $this->addSql('ALTER TABLE plan_res DROP FOREIGN KEY FK_75536115A76ED395');
        $this->addSql('ALTER TABLE plan_res DROP FOREIGN KEY FK_75536115E899029B');
        $this->addSql('DROP INDEX UNIQ_75536115A76ED395 ON plan_res');
        $this->addSql('DROP INDEX UNIQ_75536115E899029B ON plan_res');
        $this->addSql('ALTER TABLE plan_res DROP user_id, DROP plan_id');
    }
}
