/** Clase que representa un nodo de la lista enlazada */
class Nodo {
    constructor(dato, enlace = null) {
        this.dato = dato;
        this.enlace = enlace;
    }

    toString() {
        return `${this.dato} => ${this.enlace}`;
    }

    leerDato() {
        return this.dato;
    }

    siguiente() {
        return this.enlace;
    }
}

/** Clase que representa una lista enlazada simple */
class Lista {
    constructor() {
        this.primero = null;
    }

    /** Retorna el primer nodo de la lista */
    leerPrimero() {
        return this.primero;
    }

    /** Inserta un nuevo nodo al inicio de la lista */
    insertarCabezaLista(entrada) {
        const nuevo = new Nodo(entrada, this.primero);
        this.primero = nuevo;
    }

    /** Inserta un nodo después del nodo 'anterior' */
    insertarLista(anterior, entrada) {
        const nuevo = new Nodo(entrada, anterior.enlace);
        anterior.enlace = nuevo;
    }

    /** Elimina el primer nodo que contiene el valor 'entrada' */
    eliminar(entrada) {
        let actual = this.primero;
        let anterior = null;

        while (actual !== null && actual.dato !== entrada) {
            anterior = actual;
            actual = actual.enlace;
        }

        if (actual !== null) {
            if (actual === this.primero) {
                this.primero = actual.enlace;
            } else {
                anterior.enlace = actual.enlace;
            }
        }
    }

    /** Busca y retorna el nodo que contiene el valor 'destino' */
    buscarLista(destino) {
        let indice = this.primero;
        while (indice !== null) {
            if (indice.dato === destino) {
                return indice;
            }
            indice = indice.enlace;
        }
        return null;
    }

    /** Muestra en consola los valores de los nodos de la lista */
    visualizar() {
        let n = this.primero;
        const elementos = [];
        while (n !== null) {
            elementos.push(n.dato);
            n = n.enlace;
        }
        console.log(elementos.join(' '));
    }

    toString() {
        return `=> ${this.primero}`;
    }

    /** Invierte el orden de los nodos en la lista */
    invertir() {
        let anterior = null;
        let actual = this.primero;
        let siguiente = null;

        while (actual !== null) {
            siguiente = actual.enlace;
            actual.enlace = anterior;
            anterior = actual;
            actual = siguiente;
        }
        this.primero = anterior;
    }

    /** Elimina nodos duplicados de la lista */
    eliminarDuplicados() {
        const datos = new Set();
        let actual = this.primero;
        let anterior = null;

        while (actual !== null) {
            if (datos.has(actual.dato)) {
                anterior.enlace = actual.enlace;
            } else {
                datos.add(actual.dato);
                anterior = actual;
            }
            actual = actual.enlace;
        }
    }

    /**
     * Retorna el valor del nodo que está en la posición n desde el final.
     * @param {number} n - Posición desde el final (0 = último elemento)
     */
    obtenerDesdeElFinal(n) {
        let primero = this.primero;
        let segundo = this.primero;

        for (let i = 0; i < n; i++) {
            if (segundo === null) return null;
            segundo = segundo.enlace;
        }

        while (segundo !== null && segundo.enlace !== null) {
            primero = primero.enlace;
            segundo = segundo.enlace;
        }

        return primero ? primero.dato : null;
    }
}

// ========================
// Pruebas automáticas
// ========================

function pruebas() {
    // Lista vacía
    const vacia = new Lista();
    console.assert(vacia.obtenerDesdeElFinal(1) === null, "Falla obtener desde final en lista vacía");

    // Lista con un solo elemento
    const una = new Lista();
    una.insertarCabezaLista(42);
    console.assert(una.obtenerDesdeElFinal(1) === null, "Debe retornar null si n > tamaño");
    console.assert(una.obtenerDesdeElFinal(0) === 42, "Debe retornar el único elemento");

    // Lista con múltiples elementos
    const lista = new Lista();
    lista.insertarCabezaLista(3);
    lista.insertarCabezaLista(2);
    lista.insertarCabezaLista(1);
    lista.insertarCabezaLista(2);
    lista.insertarCabezaLista(1);

    console.log("\nLista inicial:");
    lista.visualizar();

    lista.eliminarDuplicados();
    console.log("\nSin duplicados:");
    lista.visualizar();
    console.assert(lista.obtenerDesdeElFinal(2) === 2, "Error en obtenerDesdeElFinal");

    lista.invertir();
    console.log("\nLista invertida:");
    lista.visualizar();
    console.assert(lista.obtenerDesdeElFinal(0) === 1, "Error en obtenerDesdeElFinal después de invertir");
}

pruebas();
