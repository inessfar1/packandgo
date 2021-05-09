<?php

namespace App\Entity;

use App\Repository\AgenceRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\HttpFoundation\File\File;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Vich\UploaderBundle\Mapping\Annotation as Vich;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;
/**
 * @ORM\Entity(repositoryClass=AgenceRepository::class)
 * @Vich\Uploadable
 */
class Agence
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     * @Groups("agences")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255 , nullable=true)
     * @Groups("agences")
     */
    private $logo;

    /**
     * @Vich\UploadableField(mapping="logo_image" ,fileNameProperty="logo")
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
     * @Groups("agences")
     */
    private $nom;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\Length(min=10,max=254,minMessage="Ce champ doit contenir au moins 10 caractères",maxMessage="Ce champ doit contenir au plus 254 caractères")
     * @Groups("agences")
     */
    private $adresse;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\Email()
     * @Groups("agences")
     */
    private $email;

    /**
     * @ORM\Column(type="integer")
     * @Assert\Length(min=6,max=30,minMessage="Ce champ doit contenir au moins 6 caractères",maxMessage="Ce champ doit contenir au plus 30 caractères")
     * @Groups("agences")
     */
    private $tel;





    /**
     * @ORM\OneToMany(targetEntity=Plan::class, mappedBy="agence")
     * @Groups("agences")
     */
    private $plans;

    public function __construct()
    {
        $this->plans = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getLogo(): ?string
    {
        return $this->logo;
    }

    public function setLogo(?string $logo): self
    {
        $this->logo = $logo;

        return $this;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getAdresse(): ?string
    {
        return $this->adresse;
    }

    public function setAdresse(string $adresse): self
    {
        $this->adresse = $adresse;

        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        $this->email = $email;

        return $this;
    }

    public function getTel(): ?int
    {
        return $this->tel;
    }

    public function setTel(int $tel): self
    {
        $this->tel = $tel;

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
            $plan->setAgence($this);
        }

        return $this;
    }

    public function removePlan(Plan $plan): self
    {
        if ($this->plans->removeElement($plan)) {
            // set the owning side to null (unless already changed)
            if ($plan->getAgence() === $this) {
                $plan->setAgence(null);
            }
        }

        return $this;
    }

}
