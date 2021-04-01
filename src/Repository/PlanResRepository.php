<?php

namespace App\Repository;

use App\Entity\PlanRes;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method PlanRes|null find($id, $lockMode = null, $lockVersion = null)
 * @method PlanRes|null findOneBy(array $criteria, array $orderBy = null)
 * @method PlanRes[]    findAll()
 * @method PlanRes[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class PlanResRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, PlanRes::class);
    }

    // /**
    //  * @return PlanRes[] Returns an array of PlanRes objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('p.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?PlanRes
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
