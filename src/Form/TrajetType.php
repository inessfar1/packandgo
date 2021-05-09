<?php

namespace App\Form;

use App\Entity\Chauffeur;
use App\Entity\Moydetran;
use Gregwar\CaptchaBundle\Type\CaptchaType;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\HiddenType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class TrajetType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('dateDepart')
            ->add('dateArrive')
            ->add('ptDepart')
            ->add('lat', HiddenType::class)
            ->add('lng', HiddenType::class)
            ->add('La_g', HiddenType::class)
            ->add('La_i', HiddenType::class)
            ->add('Ra_g', HiddenType::class)
            ->add('Ra_i', HiddenType::class)
            ->add('ptArrive')
            ->add('prix')
            ->add('idChauffeur', EntityType::class, [
                // looks for choices from this entity
                'class' => Chauffeur::class,

                'choice_label' => 'nom',

            ])
            ->add('idMoyTrans', EntityType::class, [
                // looks for choices from this entity
                'class' => Moydetran::class,

                'choice_label' => 'type',

            ])
            ->add('captcha', CaptchaType::class, array(
                'width' => 200,
                'height' => 50,
                'length' => 6,
            ));
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            // Configure your form options here
        ]);
    }
}
