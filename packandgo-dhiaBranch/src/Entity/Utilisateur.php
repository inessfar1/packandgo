<?php

namespace App\Entity;

use App\Repository\UtilisateurRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Security\Core\User\UserInterface;
use Vich\UploaderBundle\Mapping\Annotation as Vich;
use Symfony\Component\HttpFoundation\File\File;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\Validator\Constraints as Assert ;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity ;


/**
 * @ORM\Entity(repositoryClass=UtilisateurRepository::class)
 * @Vich\Uploadable
 * @UniqueEntity(
 *     fields = {"username"},
 *     message="le username existe deja"
 * )
 */
class Utilisateur implements UserInterface
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
    private $nom;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $prenom;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $image;

    /**
     * @Vich\UploadableField(mapping="utilisateur_image", fileNameProperty="image")
     */
    private $imageFile ;


    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\Email()
     */
    private $email;

    /**
     * @Assert\Length(min=5,max=10,minMessage="il faut au moin 5 carac",maxMessage="il faut au max 10 carac")
     * @ORM\Column(type="string", length=255)
     */
    private $username;

    /**
     * @Assert\Length(min=5,max=10,minMessage="il faut au moin 5 carac",maxMessage="il faut au max 10 carac")
     * @ORM\Column(type="string", length=255)
     */
    private $password;

    /**
     * @Assert\EqualTo(propertyPath="password",message="les mdp ne correspondent pas")
     */
    private $verifpassword ;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $roles;

    /**
     * @ORM\OneToMany(targetEntity=Reclamation::class, mappedBy="utilisateurs" , orphanRemoval=true)
     */
    private $reclamations;

    /**
     * @ORM\OneToMany(targetEntity=Comment::class, mappedBy="utilisateur")
     */
    private $comments;

    /**
     * @ORM\OneToMany(targetEntity=Categorie::class, mappedBy="utilisateur")
     */
    private $categories;

    /**
     * @ORM\OneToMany(targetEntity=Likecategorie::class, mappedBy="utilisateur")
     */
    private $likecategories;

    public function __construct()
    {
        $this->reclamations = new ArrayCollection();
        $this->comments = new ArrayCollection();
        $this->categories = new ArrayCollection();
        $this->likecategories = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
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

    public function getPrenom(): ?string
    {
        return $this->prenom;
    }

    public function setPrenom(string $prenom): self
    {
        $this->prenom = $prenom;

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

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        $this->email = $email;

        return $this;
    }

    public function getUsername(): ?string
    {
        return $this->username;
    }

    public function setUsername(string $username): self
    {
        $this->username = $username;

        return $this;
    }

    public function getPassword(): ?string
    {
        return $this->password;
    }

    public function setPassword(string $password): self
    {
        $this->password = $password;

        return $this;
    }

    public function getVerifpassword(): ?string
    {
        return $this->verifpassword;
    }

    public function setVerifpassword(string $verifpassword): self
    {
        $this->verifpassword = $verifpassword;

        return $this;
    }

    public function getRoles()
    {
        return [$this->roles];
    }

    public function setRoles(?string $roles): self
    {
        $this->roles = $roles;

        return $this;
    }


    public function eraseCredentials()
    {
        // TODO: Implement eraseCredentials() method.
    }

    public function getSalt()
    {
        // TODO: Implement getSalt() method.
    }


    public function getImageFile(): ?File
    {
        return $this->imageFile;
    }


    public function setImageFile(?File $imageFile = null): self
    {
        $this->imageFile = $imageFile;
        return $this ;

        //if (null !== $imageFile) {
        // It is required that at least one field changes if you are using doctrine
        // otherwise the event listeners won't be called and the file is lost
        //$this->updatedAt = new \DateTimeImmutable();
    }

    /**
     * @return Collection|Reclamation[]
     */
    public function getReclamations(): Collection
    {
        return $this->reclamations;
    }

    public function addReclamation(Reclamation $reclamation): self
    {
        if (!$this->reclamations->contains($reclamation)) {
            $this->reclamations[] = $reclamation;
            $reclamation->setUtilisateurs($this);
        }

        return $this;
    }

    public function removeReclamation(Reclamation $reclamation): self
    {
        if ($this->reclamations->removeElement($reclamation)) {
            // set the owning side to null (unless already changed)
            if ($reclamation->getUtilisateurs() === $this) {
                $reclamation->setUtilisateurs(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection|Comment[]
     */
    public function getComments(): Collection
    {
        return $this->comments;
    }

    public function addComment(Comment $comment): self
    {
        if (!$this->comments->contains($comment)) {
            $this->comments[] = $comment;
            $comment->setUtilisateur($this);
        }

        return $this;
    }

    public function removeComment(Comment $comment): self
    {
        if ($this->comments->removeElement($comment)) {
            // set the owning side to null (unless already changed)
            if ($comment->getUtilisateur() === $this) {
                $comment->setUtilisateur(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection|Categorie[]
     */
    public function getCategories(): Collection
    {
        return $this->categories;
    }

    public function addCategory(Categorie $category): self
    {
        if (!$this->categories->contains($category)) {
            $this->categories[] = $category;
            $category->setUtilisateur($this);
        }

        return $this;
    }

    public function removeCategory(Categorie $category): self
    {
        if ($this->categories->removeElement($category)) {
            // set the owning side to null (unless already changed)
            if ($category->getUtilisateur() === $this) {
                $category->setUtilisateur(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection|Likecategorie[]
     */
    public function getLikecategories(): Collection
    {
        return $this->likecategories;
    }

    public function addLikecategory(Likecategorie $likecategory): self
    {
        if (!$this->likecategories->contains($likecategory)) {
            $this->likecategories[] = $likecategory;
            $likecategory->setUtilisateur($this);
        }

        return $this;
    }

    public function removeLikecategory(Likecategorie $likecategory): self
    {
        if ($this->likecategories->removeElement($likecategory)) {
            // set the owning side to null (unless already changed)
            if ($likecategory->getUtilisateur() === $this) {
                $likecategory->setUtilisateur(null);
            }
        }

        return $this;
    }
}
