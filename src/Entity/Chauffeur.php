<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Chauffeur
 *
 * @ORM\Table(name="chauffeur")
 * @ORM\Entity
 */
class Chauffeur
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_chauffeur", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idChauffeur;

    /**
     * @var string|null
     *
     * @ORM\Column(name="nom", type="string", length=255, nullable=true)
     */
    private $nom;

    /**
     * @var string|null
     *
     * @ORM\Column(name="prenom", type="string", length=255, nullable=true)
     */
    private $prenom;

    /**
     * @var \DateTime|null
     *
     * @ORM\Column(name="date_d_n", type="date", nullable=true)
     */
    private $dateDN;
    /**
     * @var int|null
     *
     * @ORM\Column(name="disponibilite", type="integer", nullable=true)
     */
    private $disponibilite;

    public function getIdChauffeur(): ?int
    {
        return $this->idChauffeur;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(?string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getPrenom(): ?string
    {
        return $this->prenom;
    }

    public function setPrenom(?string $prenom): self
    {
        $this->prenom = $prenom;

        return $this;
    }

    public function getDateDN(): ?\DateTimeInterface
    {
        return $this->dateDN;
    }

    public function setDateDN(?\DateTimeInterface $dateDN): self
    {
        $this->dateDN = $dateDN;

        return $this;
    }
    public function getDisponibilite(): ?int
    {
        return $this->disponibilite;
    }

    public function setDisponibilite(?int $disponibilite): self
    {
        $this->disponibilite = $disponibilite;

        return $this;
    }


}
