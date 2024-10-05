import { useState, useEffect } from 'react';
import axios from 'axios';
import './App.css';

function App() {
    const [contactos, setContactos] = useState([]);
    const [nuevoContacto, setNuevoContacto] = useState({ nombre: '', telefono: '', email: '' });

    // Cargar contactos al iniciar
    useEffect(() => {
        axios.get('http://localhost:8080/api/contactos')
            .then(response => setContactos(response.data))
            .catch(error => console.error('Error al cargar los contactos:', error));
    }, []);

    // Manejar el envío del formulario para agregar un nuevo contacto
    const agregarContacto = () => {
        if (nuevoContacto.nombre && nuevoContacto.telefono && nuevoContacto.email) {
            axios.post('http://localhost:8080/api/contactos', nuevoContacto)
                .then(response => {
                    setContactos([...contactos, response.data]);
                    setNuevoContacto({ nombre: '', telefono: '', email: '' });
                })
                .catch(error => console.error('Error al agregar el contacto:', error));
        }
    };

    // Manejar eliminación de contacto
    const eliminarContacto = (id) => {
        axios.delete(`http://localhost:8080/api/contactos/${id}`)
            .then(() => setContactos(contactos.filter(c => c.id !== id)))
            .catch(error => console.error('Error al eliminar el contacto:', error));
    };

    return (
        <div className="container">
            <h1>Agenda de Contactos</h1>

            <div className="form">
                <input 
                    type="text" 
                    placeholder="Nombre" 
                    value={nuevoContacto.nombre} 
                    onChange={(e) => setNuevoContacto({ ...nuevoContacto, nombre: e.target.value })}
                />
                <input 
                    type="text" 
                    placeholder="Teléfono" 
                    value={nuevoContacto.telefono} 
                    onChange={(e) => setNuevoContacto({ ...nuevoContacto, telefono: e.target.value })}
                />
                <input 
                    type="email" 
                    placeholder="Email" 
                    value={nuevoContacto.email} 
                    onChange={(e) => setNuevoContacto({ ...nuevoContacto, email: e.target.value })}
                />
                <button onClick={agregarContacto} disabled={!nuevoContacto.nombre || !nuevoContacto.telefono || !nuevoContacto.email}>
                    Agregar Contacto
                </button>
            </div>

            <div className="contact-list">
                <h2>Contactos Guardados</h2>
                <ul>
                    {contactos.map(contacto => (
                        <li key={contacto.id}>
                            {contacto.nombre} - {contacto.telefono} - {contacto.email}
                            <button onClick={() => eliminarContacto(contacto.id)}>Eliminar</button>
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
}

export default App;
