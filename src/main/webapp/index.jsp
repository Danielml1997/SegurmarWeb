<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description"
    content="Segurmar: soluciones integrales en seguridad electrónica, videovigilancia, redes, mantenimiento y energía solar para empresas y entidades públicas en el Perú.">
  <meta name="theme-color" content="#061629">
  <title>Segurmar | Seguridad electrónica y tecnología</title>
  <link rel="preload" as="image" href="assets/images/hero-equipos-cctv-solar.jpg">
  <link rel="stylesheet" href="assets/css/styles.css">
  <!-- Ajustes independientes del encabezado: teléfono, WhatsApp y separación -->
  <link rel="stylesheet" href="assets/css/header-contact.css">
</head>

<body>
  <!-- ========== ENCABEZADO Y NAVEGACIÓN ========== -->
  <header class="header" id="inicio">
    <div class="top-contact">
      <div class="container top-contact-inner">
        <span class="top-experience">Más de 15 años de experiencia</span>
        <div class="top-contact-links">
          <a class="top-phone top-phone-primary" href="tel:+51908868523" target="_blank" rel="noopener" aria-label="Llamar al 908 868 523">
            <svg class="phone-contact-icon" viewBox="0 0 24 24" aria-hidden="true">
              <path fill="currentColor"
                    d="M6.62 10.79a15.46 15.46 0 0 0 6.59 6.59l2.2-2.2c.27-.27.67-.36 1.02-.24 1.12.37 2.33.57 3.57.57.55 0 1 .45 1 1V20c0 .55-.45 1-1 1C10.07 21 3 13.93 3 5c0-.55.45-1 1-1h3.5c.55 0 1 .45 1 1 0 1.24.2 2.45.57 3.57.11.35.03.74-.25 1.02l-2.2 2.2z"/>
          </svg>
          <span>908 868 523</span>
          </a>
          <a class="top-phone" href="https://wa.me/51986900800" target="_blank" rel="noopener"
            aria-label="Escribir por WhatsApp al 986 900 800"><svg class="wa-contact-icon" viewBox="0 0 32 32"
              aria-hidden="true">
              <path fill="currentColor"
                d="M16 3C8.82 3 3 8.66 3 15.64c0 2.46.73 4.86 2.1 6.9L3.7 28.8l6.48-1.34A13.2 13.2 0 0 0 16 28.72c7.18 0 13-5.66 13-12.64S23.18 3 16 3Zm0 23.53c-1.9 0-3.76-.5-5.38-1.45l-.38-.22-3.85.8.82-3.73-.25-.38a10.35 10.35 0 0 1-1.64-5.55c0-5.78 4.79-10.48 10.68-10.48S26.68 10.22 26.68 16 21.89 26.53 16 26.53Zm5.86-7.85c-.32-.16-1.9-.92-2.2-1.02-.3-.11-.52-.16-.74.16-.22.31-.85 1.02-1.04 1.23-.19.21-.38.24-.7.08-.32-.16-1.35-.49-2.57-1.55-.95-.83-1.59-1.85-1.78-2.16-.19-.31-.02-.48.14-.64.15-.14.32-.37.48-.55.16-.18.22-.31.32-.52.11-.21.05-.4-.03-.56-.08-.16-.74-1.75-1.01-2.4-.27-.64-.54-.55-.74-.56h-.63c-.22 0-.58.08-.88.4-.3.31-1.15 1.1-1.15 2.68 0 1.58 1.18 3.11 1.34 3.32.16.21 2.32 3.46 5.62 4.85.79.33 1.4.53 1.88.68.79.24 1.51.21 2.08.13.63-.09 1.9-.76 2.17-1.49.27-.73.27-1.35.19-1.49-.08-.13-.3-.21-.62-.37Z" />
            </svg><span>986 900 800</span></a>
          <a class="top-email" href="mailto:info@segurmartechnologies.net">✉ info@segurmartechnologies.net</a>
          <a class="top-wa" href="https://wa.me/51908868523" target="_blank" rel="noopener">WhatsApp</a>
        </div>
      </div>
    </div>
    <nav class="nav container" aria-label="Navegación principal">
      <a class="logo" href="#inicio" aria-label="Segurmar - Inicio">
        <img src="assets/images/logo-segurmar.png" alt="Segurmar - EML Inversiones y Soluciones Inalámbricas S.A.C.">
      </a>
      <button class="menu-button" aria-label="Abrir menú" aria-expanded="false">☰</button>
      <div class="nav-links">
        <a href="#inicio">Inicio</a>
        <a href="#nosotros">Nosotros</a>
        <a href="#servicios">Servicios</a>
        <a href="#equipos">Tecnologías</a>
        <a href="#proyectos">Proyectos</a>
        <a href="#contacto" class="nav-cta">Solicitar cotización</a>
        <a href="${pageContext.request.contextPath}/admin/login.xhtml" style="color:#49adf9;font-weight:700">Acceso del personal</a>
      </div>
    </nav>
  </header>

  <main>
    <!-- ========== PORTADA PRINCIPAL ========== -->
    <section class="hero">
      <div class="hero-overlay"></div>
      <div class="container hero-content reveal">
        <div class="hero-copy">
          <p class="kicker">Ingeniería • Seguridad • Conectividad</p>
          <h1>Protegemos su operación <span>con tecnología confiable</span></h1>
          <p class="lead">Diseñamos, suministramos e implementamos soluciones profesionales de videovigilancia, redes,
            control de acceso, energía solar y centros de monitoreo para empresas e instituciones en todo el Perú.</p>
          <div class="hero-actions">
            <a class="button primary" href="#contacto">Solicitar cotización</a>
            <a class="button whatsapp"
              href="https://wa.me/51908868523?text=Hola%20Segurmar,%20deseo%20cotizar%20un%20proyecto" target="_blank"
              rel="noopener">Escríbenos por WhatsApp</a>
          </div>
          <div class="hero-features">
            <span>Ingeniería y suministro</span>
            <span>Instalación profesional</span>
            <span>Mantenimiento especializado</span>
          </div>
        </div>
        <div class="hero-magazine-card" aria-label="Soluciones profesionales Segurmar">
          <span class="magazine-label">SEGURMAR PROJECTS</span>
          <img src="assets/images/camaras-ptz.jpg" alt="Sistema profesional de videovigilancia instalado por Segurmar">
          <div><strong>Soluciones listas para operar</strong><span>Ingeniería, instalación y soporte
              especializado.</span></div>
        </div>
      </div>
    </section>

    <!-- ========== VENTAJAS PRINCIPALES ========== -->
    <section class="trust-strip" aria-label="Ventajas de Segurmar">
      <div class="container trust-grid">
        <div><strong>+15 años</strong><span>de experiencia técnica</span></div>
        <div><strong>Cobertura nacional</strong><span>Lima, Ica, Pisco y todo el Perú</span></div>
        <div><strong>Atención integral</strong><span>Diseño, suministro, instalación y soporte</span></div>
        <div><strong>Marcas líderes</strong><span>Tecnología según cada proyecto</span></div>
      </div>
    </section>

    <!-- ========== MARCAS Y TECNOLOGÍAS ========== -->
    <section class="brands" aria-label="Marcas tecnológicas">
      <div class="brands-title">Tecnologías y marcas que integramos</div>
      <div class="brand-marquee">
        <div class="brand-track">
          <span class="dahua">Dahua</span><span class="hikvision">HIKVISION</span><span class="hanwha">Hanwha
            Vision</span><span>AXIS</span><span class="bosch">BOSCH</span><span>UBIQUITI</span><span
            class="cisco">CISCO</span><span class="tplink">tp-link</span><span
            class="ruijie">Ruijie</span><span>MikroTik</span><span
            class="honeywell">Honeywell</span><span>ZKTeco</span><span class="growatt">Growatt</span><span
            class="solax">SolaX</span><span class="milestone">milestone</span><span class="genetec">Genetec</span>
          <span class="dahua">Dahua</span><span class="hikvision">HIKVISION</span><span class="hanwha">Hanwha
            Vision</span><span>AXIS</span><span class="bosch">BOSCH</span><span>UBIQUITI</span><span
            class="cisco">CISCO</span><span class="tplink">tp-link</span><span
            class="ruijie">Ruijie</span><span>MikroTik</span><span
            class="honeywell">Honeywell</span><span>ZKTeco</span><span class="growatt">Growatt</span><span
            class="solax">SolaX</span><span class="milestone">milestone</span><span class="genetec">Genetec</span>
        </div>
      </div>
      <p class="brand-note">La presencia de una marca no implica representación oficial; corresponde a tecnologías que
        podemos integrar según el proyecto.</p>
    </section>

    <!-- ========== NOSOTROS ========== -->
    <section class="section about" id="nosotros">
      <div class="container about-grid">
        <div class="section-heading reveal">
          <p class="eyebrow">Quiénes somos</p>
          <h2>Experiencia técnica para proyectos que no pueden fallar</h2>
          <p><strong>Segurmar</strong> es la razón comercial de <strong>EML Inversiones &amp; Soluciones Inalámbricas
              S.A.C.</strong> Integramos ingeniería, equipamiento, instalación, configuración, capacitación y soporte en
            una sola solución.</p>
          <p>Trabajamos con empresas y entidades públicas, adaptándonos a operaciones urbanas, costeras y zonas remotas.
          </p>
        </div>
        <div class="company-card reveal">
          <h3>Información empresarial</h3>
          <div><span>Razón comercial</span><strong>Segurmar</strong></div>
          <div><span>Razón social</span><strong>EML Inversiones &amp; Soluciones Inalámbricas S.A.C.</strong></div>
          <div><span>RUC</span><strong>20522593045</strong></div>
          <div><span>Trayectoria</span><strong>Más de 15 años</strong></div>
          <div><span>Cobertura</span><strong>Atención a nivel nacional</strong></div>
        </div>
      </div>
    </section>

    <!-- ========== SERVICIOS ========== -->
    <section class="section services" id="servicios">
      <div class="container">
        <div class="section-heading reveal">
          <p class="eyebrow">Nuestras soluciones</p>
          <h2>Soluciones diseñadas para vender confianza y operar sin interrupciones</h2>
        </div>
        <div class="services-grid">
          <button class="service-card reveal" type="button" data-service="cctv"
            style="--service-image:url('../images/comercial-cctv.jpg')">
            <div class="service-icon">◉</div>
            <h3>Videovigilancia CCTV</h3>
            <p>Cámaras IP, PTZ, térmicas, NVR, VMS, analítica de video y centros de monitoreo.</p><span
              class="card-link">Ver solución →</span>
          </button>
          <button class="service-card reveal" type="button" data-service="redes"
            style="--service-image:url('../images/comercial-redes.jpg')">
            <div class="service-icon">⌁</div>
            <h3>Redes y comunicaciones</h3>
            <p>Fibra óptica, cableado estructurado, switches PoE, radioenlaces y conectividad remota.</p><span
              class="card-link">Ver solución →</span>
          </button>
          <button class="service-card reveal" type="button" data-service="acceso"
            style="--service-image:url('../images/comercial-acceso.jpg')">
            <div class="service-icon">⌂</div>
            <h3>Control de acceso</h3>
            <p>Control peatonal y vehicular, alarmas, integración y supervisión centralizada.</p><span
              class="card-link">Ver solución →</span>
          </button>
          <button class="service-card reveal" type="button" data-service="mantenimiento"
            style="--service-image:url('../images/comercial-mantenimiento.jpg')">
            <div class="service-icon">⚙</div>
            <h3>Mantenimiento</h3>
            <p>Mantenimiento preventivo, correctivo, diagnóstico, repuestos y recuperación operativa.</p><span
              class="card-link">Ver solución →</span>
          </button>
          <button class="service-card reveal" type="button" data-service="solar"
            style="--service-image:url('../images/comercial-solar.jpg')">
            <div class="service-icon">☀</div>
            <h3>Energía solar</h3>
            <p>Sistemas fotovoltaicos y respaldo energético para estaciones, torres y zonas aisladas.</p><span
              class="card-link">Ver solución →</span>
          </button>
          <button class="service-card reveal" type="button" data-service="monitoreo"
            style="--service-image:url('../images/comercial-monitoreo.jpg')">
            <div class="service-icon">◎</div>
            <h3>Centros de monitoreo</h3>
            <p>Diseño e implementación de salas de control, visualización, almacenamiento y gestión.</p><span
              class="card-link">Ver solución →</span>
          </button>
        </div>
      </div>
    </section>

    <!-- ========== TECNOLOGÍAS ========== -->
    <section class="section technologies" id="equipos">
      <div class="container">
        <div class="section-heading reveal">
          <p class="eyebrow">Equipos y tecnologías</p>
          <h2>Tecnología profesional con presentación de alto impacto</h2>
        </div>
        <div class="technology-grid">
          <article class="technology-card reveal"><img src="assets/images/camaras-ptz.jpg"
              alt="Cámaras PTZ instaladas en torre">
            <div>
              <h3>Cámaras PTZ</h3>
              <p>Vigilancia de largo alcance y monitoreo 24/7.</p>
            </div>
          </article>
          <article class="technology-card reveal"><img src="assets/images/hero-paneles.jpg"
              alt="Paneles solares para sistema autónomo">
            <div>
              <h3>Paneles solares</h3>
              <p>Energía autónoma para estaciones remotas.</p>
            </div>
          </article>
          <article class="technology-card reveal"><img src="assets/images/torre-camaras.jpg"
              alt="Torre con cámaras y enlaces">
            <div>
              <h3>Torres y radioenlaces</h3>
              <p>Conectividad y videovigilancia en zonas de difícil acceso.</p>
            </div>
          </article>
          <article class="technology-card reveal"><img src="assets/images/mantenimiento-torre.jpg"
              alt="Mantenimiento de torre de comunicaciones">
            <div>
              <h3>Mantenimiento técnico</h3>
              <p>Inspección, recuperación y protección de infraestructura.</p>
            </div>
          </article>
        </div>
      </div>
    </section>

    <!-- ========== PROYECTOS ========== -->
    <section class="section projects" id="proyectos">
      <div class="container">
        <div class="section-heading reveal">
          <p class="eyebrow">Proyectos destacados</p>
          <h2>Experiencia real convertida en resultados visibles</h2>
          <p>Fotografías propias de trabajos de campo vinculados con videovigilancia, energía autónoma y mantenimiento
            de infraestructura.</p>
        </div>
        <div class="project-gallery">
          <figure class="project large reveal"><img src="assets/images/hero-paneles.jpg"
              alt="Sistema de paneles solares en entorno costero">
            <figcaption><strong>Sistema fotovoltaico autónomo</strong><span>Energía para estaciones de monitoreo en
                zonas remotas.</span></figcaption>
          </figure>
          <figure class="project reveal"><img src="assets/images/torre-camaras.jpg"
              alt="Torre de videovigilancia con cámaras PTZ">
            <figcaption><strong>Videovigilancia en torres</strong><span>Cámaras PTZ y transmisión inalámbrica.</span>
            </figcaption>
          </figure>
          <figure class="project reveal"><img src="assets/images/torre-trabajo.jpg"
              alt="Personal realizando mantenimiento de torre">
            <figcaption><strong>Mantenimiento de estructuras</strong><span>Trabajos técnicos en infraestructura
                expuesta.</span></figcaption>
          </figure>
          <figure class="project reveal"><img src="assets/images/paneles-torre.jpg"
              alt="Paneles solares instalados junto a torre">
            <figcaption><strong>Integración de energía y comunicaciones</strong><span>Solución combinada para operación
                continua.</span></figcaption>
          </figure>
          <figure class="project reveal"><img src="assets/images/trabajo-altura.jpg"
              alt="Técnico trabajando en torre de comunicaciones">
            <figcaption><strong>Trabajo especializado en altura</strong><span>Intervención técnica y mantenimiento en
                campo.</span></figcaption>
          </figure>
        </div>
      </div>
    </section>

    <!-- ========== LLAMADO A LA ACCIÓN ========== -->
    <section class="cta-section">
      <div class="container cta-inner reveal">
        <div>
          <p class="eyebrow">Conversemos sobre su proyecto</p>
          <h2>Diseñamos la solución según su necesidad, ubicación y presupuesto.</h2>
        </div>
        <a class="button primary"
          href="https://wa.me/51908868523?text=Hola%20Segurmar,%20deseo%20recibir%20asesoría%20para%20un%20proyecto"
          target="_blank" rel="noopener">Hablar con un asesor</a>
      </div>
    </section>

    <!-- ========== CONTACTO Y COTIZACIÓN ========== -->
    <section class="section contact" id="contacto">
      <div class="container contact-grid">
        <div class="section-heading reveal">
          <p class="eyebrow">Contacto</p>
          <h2>Solicite una cotización</h2>
          <p>Completa el formulario y nuestro equipo se comunicará contigo para evaluar tu requerimiento. También puedes utilizar nuestro <a href="${pageContext.request.contextPath}/cotizacion.xhtml">formulario de cotización</a>.</p>
          <div class="contact-list">
            <a href="https://wa.me/51908868523" target="_blank" rel="noopener"><span>WhatsApp</span><strong>908 868
                523</strong></a>
            <a href="https://wa.me/51986900800" target="_blank" rel="noopener"><span>WhatsApp
                alternativo</span><strong>986 900 800</strong></a>
            <a
              href="mailto:info@segurmartechnologies.net"><span>Correo</span><strong>info@segurmartechnologies.net</strong></a>
          </div>
        </div>
          <% if (request.getAttribute("error") != null) { %>
            <p style="color:#b91c1c;font-weight:bold"><%= request.getAttribute("error") %></p>
          <% } %>
          <form class="quote-form reveal"
      id="quoteForm"
      action="${pageContext.request.contextPath}/cotizacion"
      method="post">
          <label>Nombre o empresa<input type="text" name="nombre" required></label>
          <label>Teléfono<input type="tel" name="telefono" required></label>
          <label>Correo<input type="email" name="correo"></label>
          <label>Servicio<select name="servicio">
              <option>Videovigilancia CCTV</option>
              <option>Redes y comunicaciones</option>
              <option>Control de acceso</option>
              <option>Mantenimiento</option>
              <option>Energía solar</option>
              <option>Centro de monitoreo</option>
              <option>Proyecto integral</option>
            </select></label>
          <label class="full">Descripción del proyecto<textarea name="mensaje" rows="5" required
              placeholder="Indique la ubicación, cantidad de equipos o necesidad principal."></textarea></label>
          <button class="button whatsapp full" type="submit">Enviar solicitud</button>
        </form>
      </div>
    </section>

    <!-- ========== MODAL DE DETALLE DE SERVICIOS ========== -->
    <div class="service-modal" id="serviceModal" aria-hidden="true" role="dialog" aria-modal="true"
      aria-labelledby="modalTitle">
      <div class="service-modal-backdrop" data-close-modal></div>
      <div class="service-modal-panel" role="document">
        <button class="modal-close" type="button" aria-label="Cerrar" data-close-modal>×</button>
        <div class="modal-image-wrap"><img id="modalImage" src="" alt=""></div>
        <div class="modal-content">
          <p class="eyebrow" id="modalEyebrow">Solución Segurmar</p>
          <h2 id="modalTitle"></h2>
          <p id="modalDescription"></p>
          <div class="modal-columns">
            <div>
              <h3>Alcance del servicio</h3>
              <ul id="modalScope"></ul>
            </div>
            <div class="modal-reference">
              <h3>Referencia y experiencia</h3>
              <p id="modalReference"></p>
              <p><strong>Segurmar</strong><br>EML Inversiones &amp; Soluciones Inalámbricas S.A.C.<br>RUC
                20522593045<br>Más de 15 años de experiencia</p>
            </div>
          </div>
          <div class="modal-actions">
            <a class="button whatsapp" id="modalWhatsapp" href="#" target="_blank" rel="noopener">Consultar por
              WhatsApp</a>
            <a class="modal-email" href="mailto:info@segurmartechnologies.net">info@segurmartechnologies.net</a>
          </div>
        </div>
      </div>
    </div>
  </main>

  <!-- ========== PIE DE PÁGINA ========== -->
  <footer class="footer">
    <div class="container footer-grid">
      <div class="footer-brand"><img src="assets/images/logo-segurmar.png" alt="Segurmar">
        <p>Soluciones integrales en seguridad electrónica y tecnología.</p>
      </div>
      <div>
        <h3>Contacto</h3><a href="tel:+51908868523">908 868 523</a><a href="tel:+51986900800">986 900 800</a><a
          href="mailto:info@segurmartechnologies.net">info@segurmartechnologies.net</a>
      </div>
      <div>
        <h3>Atención</h3>
        <p>Lima • Ica • Pisco</p>
        <p>Proyectos a nivel nacional</p>
      </div>
      <div>
        <h3>Razón social</h3>
        <p>EML Inversiones &amp; Soluciones Inalámbricas S.A.C.</p>
        <p>RUC 20522593045</p>
      </div>
    </div>
    <div class="copyright">© 2026 Segurmar. Todos los derechos reservados.</div>
  </footer>

  <a class="floating-whatsapp" href="https://wa.me/51908868523?text=Hola%20Segurmar,%20deseo%20información"
    target="_blank" rel="noopener" aria-label="Contactar por WhatsApp">WhatsApp</a>
  <!-- Funciones: menú móvil, animaciones, formulario y ventanas de servicios -->
  <script src="assets/js/app.js"></script>
</body>

</html>