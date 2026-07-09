# Definición de Listo (Definition of Ready)

Un escenario está **listo para automatizar** cuando:

- [ ] El **journey** está claro (qué hace el usuario y qué espera ver).
- [ ] Los **criterios de aceptación** son verificables (mensaje, saldo, estado).
- [ ] Los elementos objetivo tienen **accessibility id** (o se acuerdan con desarrollo primero).
- [ ] Los **datos** existen en el seed (o se sabe cómo agregarlos, en **ambas** implementaciones).
- [ ] Se sabe si el escenario podrá verificarse en modo simulado, real, o ambos.
- [ ] Las **reglas de negocio** involucradas están identificadas, con su mensaje esperado.

Si falta algo, se refina antes de escribir el test.
