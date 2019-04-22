# Services structure

This structure is so confusing, at first, that I felt the need to explain it here.

The basic structure is:
```text
services package
|
|_ CrudService              » A basic CRUD interface, the holy mother of them all.
|
|_ OwnerService            \
|_ PetService               | 
|_ PetTypeService           | » Basic service interfaces - for each model of the application - that extend CrudService
|_ SpecialtiesService		|     
|_ VetService              /
|
|_ map package              » The services here will use an in-memory Map for the data.
|  |
|  |_ AbstractMapService    » This abstract class declares the Map<ID, T> using Java Generics and implements all the methods.
|  |
|  |_ OwnerServiceMap      \
|  |_ PetServiceMap         | 
|  |_ PetTypeServiceMap     | » Concrete classes that extend from the AbstractMapService and implement its own basic service interface from the directory above.
|  |_ SpecialtiesServiceMap |
|  |_ VerServiceMap        /
|
|_ springdatajpa package    » Services that'll use JPA to persist data.
|  |
|  |_ OwnerSDJpaService     » Classes that implement its own basic service interface from the directory above. 
|
|_ <<name>> package         

```

The `map` package is pretty neat in the sense that the Map<ID, T> is declared once and used by every ServiceMap. Understand why [Java Generics](https://docs.oracle.com/javase/tutorial/java/generics/why.html).