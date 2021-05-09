<?php

namespace App\Entity;
use Symfony\Component\Validator\Constraints as Assert;

class RechercheOffre
{
    /**
     *@Assert\LessThanOrEqual(propertyPath="maxAnnee",message="doit etre plus petit que l'annee Max")
     */

     private $minAnnee;

    /**
     *@Assert\GreaterThanOrEqual(propertyPath="minAnnee",message="doit etre plus grand que l'annee Min")
     */

     private $maxAnnee;

    public function getminAnnee(): ?\DateTimeInterface
    {
        return $this->minAnnee;
    }

    public function setminAnnee(\DateTimeInterface $minAnnee): self
    {
        $this->minAnnee = $minAnnee;

        return $this;
    }

    public function getmaxAnnee(): ?\DateTimeInterface
    {
        return $this->maxAnnee;
    }

    public function setmaxAnnee(\DateTimeInterface $maxAnnee): self
    {
        $this->maxAnnee = $maxAnnee;

        return $this;
    }



}
?>