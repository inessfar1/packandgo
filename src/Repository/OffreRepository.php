<?php

namespace App\Repository;

use App\Entity\Offre;
use App\Entity\RechercheOffre;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\ORM\Query;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Offre|null find($id, $lockMode = null, $lockVersion = null)
 * @method Offre|null findOneBy(array $criteria, array $orderBy = null)
 * @method Offre[]    findAll()
 * @method Offre[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class OffreRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Offre::class);
    }

    public function findAllWithPagination(RechercheOffre $rechercheOffre) : Query
    {
        $req = $this->createQueryBuilder('o');
        if($rechercheOffre->getMinAnnee())
        {
            $req = $req->andWhere('o.date_debut > :min')
            ->setParameter(':min', $rechercheOffre->getMinAnnee());
        }
        if($rechercheOffre->getMaxAnnee())
        {
            $req = $req->andWhere('o.date_fin < :max')
                ->setParameter(':max', $rechercheOffre->getMaxAnnee());
        }
        return $req->getQuery();
    }

    public function findOffrebyname($name){
        return $this->createQueryBuilder('offre')
            ->where('offre.name LIKE :name')
            ->setParameter('name', '%'.$name.'%')
            ->getQuery()
            ->getResult();
    }

    public function triecroissant()
    {
        return $this->createQueryBuilder('o')
                    ->orderBy('o.price', 'ASC')
                    ->getQuery()
                    ->getResult();

    }

    public  function  triedecroissant()
    {
        return $this->createQueryBuilder('o')
                    ->orderBy('o.price','DESC')
                    ->getQuery()
                    ->getResult();
    }

    // /**
    //  * @return Offre[] Returns an array of Offre objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('o')
            ->andWhere('o.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('o.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Offre
    {
        return $this->createQueryBuilder('o')
            ->andWhere('o.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
