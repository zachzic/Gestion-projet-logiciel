package zacharie.gestion_projet_logiciel.controller;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.chart.ChartUtils;
import org.jfree.data.time.SimpleTimePeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import zacharie.gestion_projet_logiciel.dto.ActiviteSimplifieDTO;
import zacharie.gestion_projet_logiciel.dto.ProjetSimplifieDTO;
import zacharie.gestion_projet_logiciel.dto.TacheSimplifieDTO;
import zacharie.gestion_projet_logiciel.service.DiagrammeTemporelService;

import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;

@RestController
public class DiagrammeGanttController {

    @Autowired
    private DiagrammeTemporelService diagrammeTemporelService;

    @GetMapping("/diagramme-gantt")
    public void generateGanttChart(HttpServletResponse response) throws IOException {
        // Récupérer les projets avec leurs détails
        List<ProjetSimplifieDTO> projets = diagrammeTemporelService.getProjetsAvecDetails();

        if (projets.isEmpty()) {
            response.sendError(HttpServletResponse.SC_NO_CONTENT, "Aucun projet disponible pour le diagramme de Gantt");
            return;
        }

        // Créer une série de tâches
        TaskSeries taskSeries = new TaskSeries("Projets");

        // Boucler sur les projets, tâches et activités
        for (ProjetSimplifieDTO projet : projets) {
            for (TacheSimplifieDTO tache : projet.getTaches()) {
                Task task = new Task(tache.getNom(),
                        new SimpleTimePeriod(convertToDateViaInstant(tache.getDate_debut()),
                                convertToDateViaInstant(tache.getDate_fin())));

                for (ActiviteSimplifieDTO activite : tache.getActivites()) {
                    Task subTask = new Task(activite.getNom(),
                            new SimpleTimePeriod(convertToDateViaInstant(activite.getDate_debut()),
                                    convertToDateViaInstant(activite.getDate_fin())));
                    task.addSubtask(subTask);
                }

                taskSeries.add(task);
            }
        }

        // Créer le dataset
        TaskSeriesCollection dataset = new TaskSeriesCollection();
        dataset.add(taskSeries);

        // Créer le diagramme de Gantt
        JFreeChart chart = ChartFactory.createGanttChart(
                "Diagramme de Gantt des Projets",
                "Tâches",
                "Temps",
                dataset,
                true,
                true,
                false
        );

        // Envoyer l'image dans la réponse HTTP
        BufferedImage image = chart.createBufferedImage(800, 600);
        response.setContentType("image/png");
        ChartUtils.writeBufferedImageAsPNG(response.getOutputStream(), image);
    }

    // Méthode pour convertir LocalDate en Date
    private Date convertToDateViaInstant(LocalDate dateToConvert) {
        return Date.from(dateToConvert.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}