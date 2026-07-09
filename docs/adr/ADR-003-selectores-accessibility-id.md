# ADR-003 — Selectores por accessibility id

- **Estado:** Aceptada · **Fecha:** 2026-07-09

## Contexto

Los selectores son el punto de acoplamiento entre la automatización y la UI. Una mala estrategia
convierte cada rediseño en decenas de tests rotos.

## Decisión

Localizar **siempre por accessibility id** (`AppiumBy.accessibilityId`, el `content-desc` de Android),
encapsulado en los Page Objects.

## Alternativas consideradas

| Alternativa | Por qué se descartó |
|---|---|
| **XPath** | Frágil: acoplado a la jerarquía de vistas; además es el localizador más lento en Appium. |
| **Texto visible** | Se rompe con cambios de copy y no soporta internacionalización. |
| **Resource-id de Android** | Estable, pero atado a la implementación y no multiplataforma (iOS no lo tiene). |

## Consecuencias

- (+) Estable ante rediseños y cambios de texto; rápido; portable a iOS.
- (+) Beneficio extra: el `content-desc` mejora la **accesibilidad real** de la app. La testabilidad
  y la accesibilidad se refuerzan mutuamente.
- (−) Requiere acuerdo con desarrollo: los `content-desc` son un **contrato de testabilidad** que la
  app debe mantener. Se documenta en `01-la-app-y-el-contrato.md`.

## Cómo se verifica

Los Page Objects sólo usan `id(...)` (accessibility id). No hay XPath ni selectores por texto en el
repositorio.
