<?php

namespace App\Entity;

use App\Repository\PaysRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\HttpFoundation\File\File;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Vich\UploaderBundle\Mapping\Annotation as Vich;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=PaysRepository::class)
 * @Vich\Uploadable
 */
class Pays
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
    private $name;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $description;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $continent;

    /**
     * @ORM\Column(type="string", length=255,nullable=true)
     */
    private $image;

    /**
     * @Vich\UploadableField(mapping="pays_image" ,fileNameProperty="image")
     */
    private $imageFile;


    /**
     * @return mixed
     */
    public function getImageFile(): ?File
    {
        return $this->imageFile;
    }

    /**
     * @param mixed $imageFile
     */
    public function setImageFile(?File $imageFile=null): self
    {
        $this->imageFile = $imageFile;
        if($this>$imageFile instanceof  UploadedFile){
            $this->update_at=new \DateTime('now');
        }
        return $this;
    }

    /**
     * @ORM\Column(type="datetime", nullable=true)
     */
    private $updated_at;



    /**
     * @ORM\OneToMany(targetEntity=Agence::class, mappedBy="pays")
     */
    private $agences;

    /**
     * @ORM\OneToMany(targetEntity=Plan::class, mappedBy="pays")
     */
    private $plans;

    public function __construct()
    {
        $this->agences = new ArrayCollection();
        $this->plans = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getName(): ?string
    {
        return $this->name;
    }

    public function setName(string $name): self
    {
        $this->name = $name;

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

    public function getContinent(): ?string
    {
        return $this->continent;
    }

    public function setContinent(string $continent): self
    {
        $this->continent = $continent;

        return $this;
    }

    public function getImage(): ?string
    {
        return $this->image;
    }

    public function setImage(?string $image): self
    {
        $this->image = $image;

        return $this;
    }

    /**
     * @return Collection|Agence[]
     */
    public function getAgences(): Collection
    {
        return $this->agences;
    }

    public function addAgence(Agence $agence): self
    {
        if (!$this->agences->contains($agence)) {
            $this->agences[] = $agence;
            $agence->setPays($this);
        }

        return $this;
    }

    public function removeAgence(Agence $agence): self
    {
        if ($this->agences->removeElement($agence)) {
            // set the owning side to null (unless already changed)
            if ($agence->getPays() === $this) {
                $agence->setPays(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection|Plan[]
     */
    public function getPlans(): Collection
    {
        return $this->plans;
    }

    public function addPlan(Plan $plan): self
    {
        if (!$this->plans->contains($plan)) {
            $this->plans[] = $plan;
            $plan->setPays($this);
        }

        return $this;
    }

    public function removePlan(Plan $plan): self
    {
        if ($this->plans->removeElement($plan)) {
            // set the owning side to null (unless already changed)
            if ($plan->getPays() === $this) {
                $plan->setPays(null);
            }
        }

        return $this;
    }
    public function getUpdatedAt(): ?\DateTimeInterface
    {
        return $this->updated_at;
    }

    public function setUpdatedAt(\DateTimeInterface $updated_at): self
    {
        $this->updated_at = $updated_at;

        return $this;
    }
}
