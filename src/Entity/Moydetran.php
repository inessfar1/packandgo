<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Moydetran
 *
 * @ORM\Table(name="moydetran")
 * @ORM\Entity
 */
class Moydetran
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_moy_trans", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idMoyTrans;

    /**
     * @var string|null
     *
     * @ORM\Column(name="type", type="string", length=255, nullable=true)
     */
    private $type;

    /**
     * @var string|null
     *
     * @ORM\Column(name="matricule", type="string", length=255, nullable=true)
     */
    private $matricule;

    /**
     * @var int|null
     *
     * @ORM\Column(name="disponibilite", type="integer", nullable=true)
     */
    private $disponibilite;

    public function getIdMoyTrans(): ?int
    {
        return $this->idMoyTrans;
    }

    public function getType(): ?string
    {
        return $this->type;
    }

    public function setType(?string $type): self
    {
        $this->type = $type;

        return $this;
    }

    public function getMatricule(): ?string
    {
        return $this->matricule;
    }

    public function setMatricule(?string $matricule): self
    {
        $this->matricule = $matricule;

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
