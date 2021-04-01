<?php

namespace App\Repository;

use App\Entity\Plan;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Plan|null find($id, $lockMode = null, $lockVersion = null)
 * @method Plan|null findOneBy(array $criteria, array $orderBy = null)
 * @method Plan[]    findAll()
 * @method Plan[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class PlanRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Plan::class);
    }

    /**
     * @return Plan[]
     */
    public function findPlanBySujet($sujet){
        return $this->createQueryBuilder('plan')
            ->andWhere('plan.sujet LIKE :sujet')
            ->setParameter('sujet', '%'.$sujet.'%')
            ->getQuery()
            ->getResult();
    }
    /**
     * @return Plan[]
     */
    public function triSujetASC(){
        return $this->createQueryBuilder('t')
            ->orderBy('t.sujet','ASC')
            ->getQuery()
            ->getResult();
    }
    /**
     * @return Plan[]
     */
    public function triSujetDESC(){
        return $this->createQueryBuilder('p')
            ->orderBy('p.sujet','DESC')
            ->getQuery()
            ->getResult();
    }

    // /**
    //  * @return Plan[] Returns an array of Plan objects
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
    public function findOneBySomeField($value): ?Plan
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
