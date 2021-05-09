<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210507192541 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE agence DROP FOREIGN KEY FK_64C19AA9A6E44244');
        $this->addSql('DROP INDEX IDX_64C19AA9A6E44244 ON agence');
        $this->addSql('ALTER TABLE agence DROP pays_id');
        $this->addSql('ALTER TABLE plan DROP FOREIGN KEY FK_DD5A5B7DA6E44244');
        $this->addSql('ALTER TABLE plan DROP FOREIGN KEY FK_DD5A5B7DD725330D');
        $this->addSql('DROP INDEX IDX_DD5A5B7DA6E44244 ON plan');
        $this->addSql('ALTER TABLE plan DROP pays_id, DROP nbr, DROP rec');
        $this->addSql('ALTER TABLE plan ADD CONSTRAINT FK_DD5A5B7DD725330D FOREIGN KEY (agence_id) REFERENCES agence (id)');
        $this->addSql('ALTER TABLE plan_res DROP FOREIGN KEY FK_75536115A76ED395');
        $this->addSql('ALTER TABLE plan_res DROP FOREIGN KEY FK_75536115E899029B');
        $this->addSql('ALTER TABLE plan_res ADD CONSTRAINT FK_75536115A76ED395 FOREIGN KEY (user_id) REFERENCES utilisateur (id)');
        $this->addSql('ALTER TABLE plan_res ADD CONSTRAINT FK_75536115E899029B FOREIGN KEY (plan_id) REFERENCES plan (id)');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE agence ADD pays_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE agence ADD CONSTRAINT FK_64C19AA9A6E44244 FOREIGN KEY (pays_id) REFERENCES pays (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('CREATE UNIQUE INDEX IDX_64C19AA9A6E44244 ON agence (pays_id)');
        $this->addSql('ALTER TABLE plan DROP FOREIGN KEY FK_DD5A5B7DD725330D');
        $this->addSql('ALTER TABLE plan ADD pays_id INT DEFAULT NULL, ADD nbr INT DEFAULT 10 NOT NULL, ADD rec INT DEFAULT 0 NOT NULL');
        $this->addSql('ALTER TABLE plan ADD CONSTRAINT FK_DD5A5B7DA6E44244 FOREIGN KEY (pays_id) REFERENCES pays (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE plan ADD CONSTRAINT FK_DD5A5B7DD725330D FOREIGN KEY (agence_id) REFERENCES agence (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('CREATE INDEX IDX_DD5A5B7DA6E44244 ON plan (pays_id)');
        $this->addSql('ALTER TABLE plan_res DROP FOREIGN KEY FK_75536115A76ED395');
        $this->addSql('ALTER TABLE plan_res DROP FOREIGN KEY FK_75536115E899029B');
        $this->addSql('ALTER TABLE plan_res ADD CONSTRAINT FK_75536115A76ED395 FOREIGN KEY (user_id) REFERENCES utilisateur (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE plan_res ADD CONSTRAINT FK_75536115E899029B FOREIGN KEY (plan_id) REFERENCES plan (id) ON UPDATE CASCADE ON DELETE CASCADE');
    }
}
