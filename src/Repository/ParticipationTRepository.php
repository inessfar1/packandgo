<?php

namespace App\Repository;

use App\Entity\ParticipationT;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method ParticipationT|null find($id, $lockMode = null, $lockVersion = null)
 * @method ParticipationT|null findOneBy(array $criteria, array $orderBy = null)
 * @method ParticipationT[]    findAll()
 * @method ParticipationT[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ParticipationTRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, ParticipationT::class);
    }

    // /**
    //  * @return ParticipationT[] Returns an array of ParticipationT objects
    //  */

    public function findByUser($value)
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.idUser = :val')
            ->setParameter('val', $value)
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }


/*
    public function findByUser($value): ?ParticipationT
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.idUser = :val')
            ->setParameter('val', $value)
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
*/
}
