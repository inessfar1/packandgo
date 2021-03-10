<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Chauffeur
 *
 * @ORM\Table(name="chauffeur")
 * @ORM\Entity(repositoryClass="App\Repository\ChauffeurRepository")
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
     * @Assert\NotBlank
     * @Assert\Regex(
     *     pattern     = "/^[a-z]+$/i",
     *     htmlPattern = "^[a-zA-Z]+$",
     *      message= "verifier votre nom"
     * )
     *
     * @ORM\Column(name="nom", type="string", length=255, nullable=true)
     */
    private $nom;

    /**
     * @var string|null
     * @Assert\NotBlank
     * @Assert\Regex(
     *     pattern     = "/^[a-z]+$/i",
     *     htmlPattern = "^[a-zA-Z]+$",
     *      message= "verifier votre prenom"
     * )
     * @ORM\Column(name="prenom", type="string", length=255, nullable=true)
     */
    private $prenom;


    /**
     * @var int|null
     * @Assert\NotBlank
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
