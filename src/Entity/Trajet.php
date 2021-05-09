<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * Trajet
 *
 * @ORM\Table(name="trajet", indexes={@ORM\Index(name="FN_key", columns={"id_chauffeur"}), @ORM\Index(name="FN_keymoy", columns={"id_moy_trans"})})
 * @ORM\Entity(repositoryClass="App\Repository\TrajetRepository")
 */
class Trajet
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_trajet", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idTrajet;

    /**
     * @var \DateTime|null
     *
     * @ORM\Column(name="date_depart", type="date", nullable=true)
     */
    private $dateDepart;

    /**
     * @var \DateTime|null
     * @Assert\NotBlank
     * @ORM\Column(name="date_arrive", type="date", nullable=true)
     */
    private $dateArrive;

    /**
     * @var string|null
     *  @Assert\NotBlank
     * @Assert\NotNull
     * @ORM\Column(name="pt_depart", type="string", length=255, nullable=true)
     */
    private $ptDepart;

    /**
     * @var string|null
     *  @Assert\NotBlank
     * @Assert\NotNull
     * @ORM\Column(name="pt_arrive", type="string", length=255, nullable=true)
     */
    private $ptArrive;

    /**
     * @var \Chauffeur
     * @Assert\NotBlank
     * @ORM\ManyToOne(targetEntity="Chauffeur")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_chauffeur", referencedColumnName="id_chauffeur")
     * })
     */
    private $idChauffeur;

    /**
     * @var float|null
     * @Assert\NotBlank
     * @ORM\Column(name="prix", type="float", precision=10, scale=0, nullable=true)
     */
    private $prix;



    /**
     * @var \Moydetran
     * @Assert\NotBlank
     * @ORM\ManyToOne(targetEntity="Moydetran")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_moy_trans", referencedColumnName="id_moy_trans")
     * })
     */
    private $idMoyTrans;

    /**
     * @var float
     *
     * @ORM\Column(name="lat", type="float", precision=10, scale=0, nullable=false)
     */
    private $lat;

    /**
     * @var float
     *
     * @ORM\Column(name="lng", type="float", precision=10, scale=0, nullable=false)
     */
    private $lng;

    /**
     * @var float
     *
     * @ORM\Column(name="La_g", type="float", precision=10, scale=0, nullable=false)
     */
    private $La_g;

    /**
     * @var float
     *
     * @ORM\Column(name="La_i", type="float", precision=10, scale=0, nullable=false)
     */
    private $La_i;

    /**
     * @var float
     *
     * @ORM\Column(name="Ra_g", type="float", precision=10, scale=0, nullable=false)
     */
    private $Ra_g;

    /**
     * @var float
     *
     * @ORM\Column(name="Ra_i", type="float", precision=10, scale=0, nullable=false)
     */
    private $Ra_i;


    public function getIdTrajet(): ?int
    {
        return $this->idTrajet;
    }

    public function getDateDepart(): ?\DateTimeInterface
    {
        return $this->dateDepart;
    }

    public function setDateDepart(?\DateTimeInterface $dateDepart): self
    {
        $this->dateDepart = $dateDepart;

        return $this;
    }

    public function getDateArrive(): ?\DateTimeInterface
    {
        return $this->dateArrive;
    }

    public function setDateArrive(?\DateTimeInterface $dateArrive): self
    {
        $this->dateArrive = $dateArrive;

        return $this;
    }

    public function getPtDepart(): ?string
    {
        return $this->ptDepart;
    }

    public function setPtDepart(?string $ptDepart): self
    {
        $this->ptDepart = $ptDepart;

        return $this;
    }

    public function getPtArrive(): ?string
    {
        return $this->ptArrive;
    }

    public function setPtArrive(?string $ptArrive): self
    {
        $this->ptArrive = $ptArrive;

        return $this;
    }



    public function getPrix(): ?float
    {
        return $this->prix;
    }

    public function setPrix(?float $prix): self
    {
        $this->prix = $prix;

        return $this;
    }

    public function getIdMoyTrans(): ?Moydetran
    {
        return $this->idMoyTrans;
    }

    public function setIdMoyTrans(?Moydetran $idMoyTrans): self
    {
        $this->idMoyTrans = $idMoyTrans;

        return $this;
    }

    public function getIdChauffeur(): ?Chauffeur
    {
        return $this->idChauffeur;
    }

    public function setIdChauffeur(?Chauffeur $idChauffeur): void
    {
        $this->idChauffeur = $idChauffeur;
    }
    public function setLocation(string $location): self
    {
        $this->location = $location;

        return $this;
    }

    public function getLaG(): ?float
    {
        return $this->La_g;
    }

    public function setLaG(float $La_g): self
    {
        $this->La_g = $La_g;

        return $this;
    }

    public function getLaI(): ?float
    {
        return $this->La_i;
    }

    public function setLaI(float $La_i): self
    {
        $this->La_i = $La_i;

        return $this;
    }

    public function getRaG(): ?float
    {
        return $this->Ra_g;
    }

    public function setRaG(float $Ra_g): self
    {
        $this->Ra_g = $Ra_g;

        return $this;
    }

    public function getRaI(): ?float
    {
        return $this->Ra_i;
    }

    public function setRaI(float $Ra_i): self
    {
        $this->Ra_i = $Ra_i;

        return $this;
    }

    public function getLat(): ?float
    {
        return $this->lat;
    }

    public function setLat(float $lat): self
    {
        $this->lat = $lat;

        return $this;
    }

    public function getLng(): ?float
    {
        return $this->lng;
    }

    public function setLng(float $lng): self
    {
        $this->lng = $lng;

        return $this;
    }


}
