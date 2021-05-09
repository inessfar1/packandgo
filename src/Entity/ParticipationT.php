<?php

namespace App\Entity;

use App\Repository\ParticipationTRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * ParticipationT
 * @ORM\Table(name="ParticipationT")
 * @ORM\Entity(repositoryClass="App\Repository\ParticipationTRepository")
 */
class ParticipationT
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="integer", nullable=true)
     */
    private $idUser;


    /**
     * @var \Trajet
     *
     * @ORM\ManyToOne(targetEntity="Trajet")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_trajet", referencedColumnName="id_trajet")
     * })
     */
    private $idTrajet;



    public function getId(): ?int
    {
        return $this->id;
    }

    public function getIdUser(): ?int
    {
        return $this->idUser;
    }

    public function setIdUser(?int $idUser): self
    {
        $this->idUser = $idUser;

        return $this;
    }

    public function getIdTrajet(): ?Trajet
    {
        return $this->idTrajet;
    }

    public function setIdTrajet(?Trajet $Trajet): void
    {
        $this->idTrajet = $Trajet;
        
    }


}
