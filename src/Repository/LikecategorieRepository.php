<?php

namespace App\Repository;

use App\Entity\Likecategorie;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Likecategorie|null find($id, $lockMode = null, $lockVersion = null)
 * @method Likecategorie|null findOneBy(array $criteria, array $orderBy = null)
 * @method Likecategorie[]    findAll()
 * @method Likecategorie[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class LikecategorieRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Likecategorie::class);
    }

    // /**
    //  * @return Likecategorie[] Returns an array of Likecategorie objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('l')
            ->andWhere('l.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('l.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */


    public function findOneBySomeField($value,$value2)
    {
        return $this->createQueryBuilder('l')
            ->Where('l.Categorie = :val')
            ->andWhere('l.utilisateur = :val1')
            ->setParameter('val', $value)
            ->setParameter('val1', $value2)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }

}
