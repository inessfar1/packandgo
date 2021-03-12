<?php

namespace App\Entity;

use App\Repository\ReclamationRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=ReclamationRepository::class)
 */
class Reclamation
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $object;

    /**
     * @ORM\Column(type="text")
     */
    private $description;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $statut;

    /**
     * @ORM\ManyToOne(targetEntity=Utilisateur::class, inversedBy="reclamations")
     */
    private $utilisateurs;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getObject(): ?string
    {
        return $this->object;
    }

    public function setObject(string $object): self
    {
        $this->object = $object;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }

    public function getStatut(): ?string
    {
        return $this->statut;
    }

    public function setStatut(string $statut): self
    {
        $this->statut = $statut;

        return $this;
    }

    public function getUtilisateurs(): ?Utilisateur
    {
        return $this->utilisateurs;
    }

    public function setUtilisateurs(Utilisateur $utilisateurs): self
    {
        $this->utilisateurs = $utilisateurs;

        return $this;
    }
}
