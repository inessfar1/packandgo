<?php

namespace App\Entity;

use App\Repository\ChambreRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\HttpFoundation\File\File;
use Vich\UploaderBundle\Mapping\Annotation as Vich;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Vich\UploaderBundle\Mapping\Annotationh\UploadableField;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=ChambreRepository::class)
 * @vich\Uploadable
 */
class Chambre
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * * @Assert\Regex(
     *     pattern     = "/^[a-z]+$/i",
     *     htmlPattern = "^[a-zA-Z]+$",
     *      message= "Il faut que le type soit alphabetique"
     * )
     * @Assert\Length(
     *      min = 4,
     *      max = 20,
     *      minMessage = "minmum {{ limit }} charactéres",
     *      maxMessage = "maximum {{ limit }} charactéres"
     * )
     */
    private $type;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $vue;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $duree;

    /**
     * @ORM\Column(type="float")
     */
    private $prix;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $image;


    /**
     * @ORM\ManyToOne(targetEntity=Hotel::class, inversedBy="chambres")
     */
    private $hotel;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getType(): ?string
    {
        return $this->type;
    }

    public function setType(string $type): self
    {
        $this->type = $type;

        return $this;
    }

    public function getVue(): ?string
    {
        return $this->vue;
    }

    public function setVue(string $vue): self
    {
        $this->vue = $vue;

        return $this;
    }

    public function getDuree(): ?string
    {
        return $this->duree;
    }

    public function setDuree(string $duree): self
    {
        $this->duree = $duree;

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

    public function getImage(): ?string
    {
        return $this->image;
    }

    public function setImage(?string $image): self
    {
        $this->image = $image;

        return $this;
    }

    public function getHotel(): ?Hotel
    {
        return $this->hotel;
    }

    public function setHotel(?Hotel $hotel): self
    {
        $this->hotel = $hotel;

        return $this;
    }
    /**
     * @Vich\UploadableField(mapping="chambre_image", fileNameProperty="image")
     */

    private $imageFile;

    /**
     * @ORM\OneToOne(targetEntity=ChambreRes::class, mappedBy="chambre", cascade={"persist", "remove"})
     */
    private $chambreRes;



    public function getImageFile(): ?File
    {
        return $this->imageFile;
    }

    public function setImageFile(?File $imageFile = null): self
    {
        $this->imageFile = $imageFile;
        // if($this->imageFile instanceof UploadableField)
        //{
        // $this->updated_at = new \DateTime('now');

        // }

        return $this;

    }

    public function getChambreRes(): ?ChambreRes
    {
        return $this->chambreRes;
    }

    public function setChambreRes(?ChambreRes $chambreRes): self
    {
        // unset the owning side of the relation if necessary
        if ($chambreRes === null && $this->chambreRes !== null) {
            $this->chambreRes->setChambre(null);
        }

        // set the owning side of the relation if necessary
        if ($chambreRes !== null && $chambreRes->getChambre() !== $this) {
            $chambreRes->setChambre($this);
        }

        $this->chambreRes = $chambreRes;

        return $this;
    }


}
