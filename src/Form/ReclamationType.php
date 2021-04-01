<?php

namespace App\Form;

use App\Entity\Reclamation;
use App\Entity\Utilisateur;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;

class ReclamationType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('object',ChoiceType::class,[
            'choices' => [
                    'Transport' => 'trasport',
                    'Inscription' => 'inscription',
                    'Blog et Froum' => 'blog',
                    'Plan attraction' => 'plan',
                    'Hotel' => 'hotel',
                    'Offre' => 'offre',
                     ],
                ])
            ->add('description')




        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Reclamation::class,
        ]);
    }
}
