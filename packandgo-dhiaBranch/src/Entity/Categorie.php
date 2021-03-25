<?php

namespace App\Entity;

use App\Repository\CategorieRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=CategorieRepository::class)
 */
class Categorie
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
    private $Title;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $description;

    /**
     * @ORM\OneToMany(targetEntity=Comment::class, mappedBy="Categorie")
     */
    private $comments;

    /**
     * @ORM\ManyToOne(targetEntity=Utilisateur::class, inversedBy="categories")
     */
    private $utilisateur;

    /**
     * @ORM\OneToMany(targetEntity=Likecategorie::class, mappedBy="Categorie")
     */
    private $likecategories;

    public function __construct()
    {
        $this->comments = new ArrayCollection();
        $this->likecategories = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getTitle(): ?string
    {
        return $this->Title;
    }

    public function setTitle(string $Title): self
    {
        $this->Title = $Title;

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
            $comment->setCategorie($this);
        }

        return $this;
    }

    public function removeComment(Comment $comment): self
    {
        if ($this->comments->removeElement($comment)) {
            // set the owning side to null (unless already changed)
            if ($comment->getCategorie() === $this) {
                $comment->setCategorie(null);
            }
        }

        return $this;
    }

    public function getUtilisateur(): ?Utilisateur
    {
        return $this->utilisateur;
    }

    public function setUtilisateur(?Utilisateur $utilisateur): self
    {
        $this->utilisateur = $utilisateur;

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
            $likecategory->setCategorie($this);
        }

        return $this;
    }

    public function removeLikecategory(Likecategorie $likecategory): self
    {
        if ($this->likecategories->removeElement($likecategory)) {
            // set the owning side to null (unless already changed)
            if ($likecategory->getCategorie() === $this) {
                $likecategory->setCategorie(null);
            }
        }

        return $this;
    }
}
