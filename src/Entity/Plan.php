<?php

namespace App\Entity;

use App\Repository\PlanRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\HttpFoundation\File\File;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\Serializer\Annotation\Groups;
use Vich\UploaderBundle\Mapping\Annotation as Vich;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=PlanRepository::class)
 * @Vich\Uploadable
 */
class Plan
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     * @Groups("plans")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255,nullable=true)
     * @Groups("plans")
     */
    private $image;


    /**
     * @Vich\UploadableField(mapping="logo_image" ,fileNameProperty="image")
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
        }
        return $this;
    }
    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\Length(min=6,max=30,minMessage="Ce champ doit contenir au moins 6 caractères",maxMessage="Ce champ doit contenir au plus 30 caractères")
     * @Groups("plans")
     */
    private $sujet;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\Length(min=10,max=254,minMessage="Ce champ doit contenir au moins 10 caractères",maxMessage="Ce champ doit contenir au plus 254 caractères")
     * @Groups("plans")
     */
    private $description;

    /**
     * @ORM\Column(type="date")
     * @Assert\GreaterThan("today")
     * @Groups("plans")
     */
    private $date;

    /**
     * @ORM\Column(type="float")
     * @Groups("plans")
     */
    private $prix;



    /**
     * @ORM\Column(type="integer", nullable=true)
     * @Groups("plans")
     */
    private $rec;

    public function getRec(): ?int
    {
        return $this->rec;
    }

    public function setRec(string $rec): self
    {
        $this->rec= $rec;

        return $this;
    }

    /**
     * @ORM\Column(type="integer", nullable=true)
     * @Groups("plans")
     */
    private $nbr;

    public function getNbr(): ?int
    {
        return $this->nbr;
    }

    public function setNbr(string $nbr): self
    {
        $this->nbr= $nbr;

        return $this;
    }

    /**
     * @ORM\ManyToOne(targetEntity=Agence::class, inversedBy="plans")
     * @Groups("plans")
     */
    private $agence;

  

    public function getId(): ?int
    {
        return $this->id;
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

    public function getSujet(): ?string
    {
        return $this->sujet;
    }

    public function setSujet(string $sujet): self
    {
        $this->sujet = $sujet;

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

    public function getDate(): ?\DateTimeInterface
    {
        return $this->date;
    }

    public function setDate(\DateTimeInterface $date): self
    {
        $this->date = $date;

        return $this;
    }

    public function getPrix(): ?float
    {
        return $this->prix;
    }

    public function setPrix(float $prix): self
    {
        $this->prix = $prix;

        return $this;
    }





    public function getAgence(): ?Agence
    {
        return $this->agence;
    }

    public function setAgence(?Agence $agence): self
    {
        $this->agence = $agence;

        return $this;
    }



}
