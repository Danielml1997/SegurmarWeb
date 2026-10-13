const menuButton = document.querySelector('.menu-button');
const navLinks = document.querySelector('.nav-links');

menuButton?.addEventListener('click', () => {
  const open = navLinks.classList.toggle('open');
  menuButton.setAttribute('aria-expanded', String(open));
});

navLinks?.querySelectorAll('a').forEach(a => 
  a.addEventListener('click', () => {
    navLinks.classList.remove('open');
    menuButton?.setAttribute('aria-expanded', 'false');
  })
);


// ======================================================
// ANIMACIONES AL HACER SCROLL
// ======================================================

const observer = new IntersectionObserver(entries => {
  entries.forEach(entry => {
    if (entry.isIntersecting) {
      entry.target.classList.add('visible');
      observer.unobserve(entry.target);
    }
  });
}, { threshold: 0.12 });

document.querySelectorAll('.reveal').forEach(el => observer.observe(el));


// ======================================================
// INFORMACIÓN DE LOS SERVICIOS
// ======================================================

const serviceDetails = {

  cctv: {
    title: 'Videovigilancia CCTV',
    image: 'assets/images/comercial-cctv.jpg',
    alt: 'Sistema profesional de videovigilancia CCTV con cámaras de seguridad',

    description:
      'Diseñamos e implementamos sistemas de videovigilancia IP para supervisión continua, grabación, analítica y gestión centralizada.',

    scope: [
      'Cámaras IP, domo, bullet, PTZ y térmicas',
      'NVR, servidores, almacenamiento y VMS',
      'Analítica de video, integración y visualización remota',
      'Instalación, configuración, capacitación y soporte'
    ],

    reference:
      'Experiencia en sistemas instalados en torres y estaciones remotas, incluyendo cámaras PTZ, transmisión inalámbrica y alimentación autónoma en entornos costeros.'
  },


  redes: {
    title: 'Redes y comunicaciones',
    image: 'assets/images/comercial-redes.jpg',
    alt: 'Infraestructura profesional de redes y comunicaciones',

    description:
      'Implementamos infraestructura de comunicaciones segura y estable para enlazar cámaras, sedes, centros de control y estaciones remotas.',

    scope: [
      'Radioenlaces punto a punto y punto multipunto',
      'Fibra óptica y cableado estructurado',
      'Switches PoE, routers, Wi-Fi empresarial y networking',
      'Diagnóstico, optimización y documentación de red'
    ],

    reference:
      'Trabajos en torres de comunicaciones y zonas de difícil acceso, integrando conectividad inalámbrica con sistemas de videovigilancia.'
  },


  acceso: {
    title: 'Control de acceso',
    image: 'assets/images/comercial-acceso.jpg',
    alt: 'Infraestructura de seguridad electrónica para supervisión de accesos',

    description:
      'Integramos control peatonal y vehicular con videovigilancia, alarmas y supervisión desde un puesto central.',

    scope: [
      'Lectores, cerraduras, controladores y pulsadores',
      'Control vehicular, barreras e identificación',
      'Integración con CCTV y sistemas de alarma',
      'Registro, administración y reportes de eventos'
    ],

    reference:
      'La solución se diseña de acuerdo con los puntos de ingreso, niveles de autorización y necesidades operativas de cada empresa o entidad.'
  },


  mantenimiento: {
    title: 'Mantenimiento especializado',
    image: 'assets/images/comercial-mantenimiento.jpg',
    alt: 'Técnico especializado realizando mantenimiento de equipos',

    description:
      'Recuperamos y preservamos la operación de sistemas de seguridad, comunicaciones, energía e infraestructura instalada.',

    scope: [
      'Mantenimiento preventivo y correctivo',
      'Diagnóstico de fallas y recuperación operativa',
      'Cambio de equipos, repuestos, cableado y conectores',
      'Inspección de torres, estructuras y sistemas expuestos'
    ],

    reference:
      'Experiencia real en mantenimiento de cámaras, torres y estructuras sometidas a humedad, arena, corrosión y fuertes vientos.'
  },


  solar: {
    title: 'Energía solar fotovoltaica',
    image: 'assets/images/comercial-solar.jpg',
    alt: 'Sistema comercial de energía solar fotovoltaica',

    description:
      'Desarrollamos sistemas fotovoltaicos autónomos y de respaldo para equipos de seguridad, telecomunicaciones y cargas esenciales.',

    scope: [
      'Paneles solares, controladores, baterías e inversores',
      'Cálculo de consumo y autonomía requerida',
      'Protecciones, estructuras e instalación eléctrica',
      'Sistemas para torres, estaciones y zonas aisladas'
    ],

    reference:
      'Instalación y mantenimiento de sistemas solares que alimentan estaciones de monitoreo y comunicaciones en la Reserva Nacional de Paracas.'
  },


  monitoreo: {
    title: 'Centros de monitoreo',
    image: 'assets/images/comercial-monitoreo.jpg',
    alt: 'Centro moderno de monitoreo y videovigilancia',

    description:
      'Diseñamos salas de control para visualizar, gestionar y almacenar información procedente de cámaras y sistemas de seguridad.',

    scope: [
      'Videowall, estaciones de operación y mobiliario técnico',
      'VMS, servidores, almacenamiento y respaldo',
      'Integración de CCTV, alarmas y control de acceso',
      'Configuración, capacitación y puesta en marcha'
    ],

    reference:
      'Propuestas orientadas a centros de control de empresas y entidades públicas, adaptadas al número de cámaras, operadores y tiempo de almacenamiento.'
  }
};


// ======================================================
// MODAL DE SERVICIOS
// ======================================================

const modal = document.getElementById('serviceModal');
const modalTitle = document.getElementById('modalTitle');
const modalImage = document.getElementById('modalImage');
const modalDescription = document.getElementById('modalDescription');
const modalScope = document.getElementById('modalScope');
const modalReference = document.getElementById('modalReference');
const modalWhatsapp = document.getElementById('modalWhatsapp');

let lastServiceTrigger = null;


// ======================================================
// ABRIR MODAL
// ======================================================

function openServiceModal(key, trigger) {

  const detail = serviceDetails[key];

  if (!detail || !modal) {
    return;
  }

  lastServiceTrigger = trigger || null;

  modalTitle.textContent = detail.title;

  modalImage.src = detail.image;
  modalImage.alt = detail.alt;

  modalDescription.textContent = detail.description;
  modalReference.textContent = detail.reference;

  modalScope.innerHTML = detail.scope
    .map(item => `<li>${item}</li>`)
    .join('');

  const message =
    `Hola Segurmar, deseo información y una cotización sobre ${detail.title}.`;

  modalWhatsapp.href =
    `https://wa.me/51908868523?text=${encodeURIComponent(message)}`;

  modal.classList.add('open');

  modal.setAttribute('aria-hidden', 'false');

  document.body.classList.add('modal-open');

  modal.querySelector('.modal-close')?.focus();
}


// ======================================================
// CERRAR MODAL
// ======================================================

function closeServiceModal() {

  if (!modal) {
    return;
  }

  modal.classList.remove('open');

  modal.setAttribute('aria-hidden', 'true');

  document.body.classList.remove('modal-open');

  lastServiceTrigger?.focus();
}


// ======================================================
// EVENTOS DE LAS TARJETAS DE SERVICIOS
// ======================================================

document.querySelectorAll('[data-service]').forEach(card => {

  card.addEventListener('click', () => {

    openServiceModal(
      card.dataset.service,
      card
    );

  });

});


// ======================================================
// BOTONES PARA CERRAR MODAL
// ======================================================

document.querySelectorAll('[data-close-modal]').forEach(item => {

  item.addEventListener(
    'click',
    closeServiceModal
  );

});


// ======================================================
// CERRAR MODAL CON ESC
// ======================================================

document.addEventListener('keydown', event => {

  if (
    event.key === 'Escape' &&
    modal?.classList.contains('open')
  ) {

    closeServiceModal();

  }

});
