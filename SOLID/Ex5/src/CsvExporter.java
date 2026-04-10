import java.nio.charset.StandardCharsets;

public class CsvExporter implements Exporter {
    @Override
    public ExportResult export(ExportRequest req) {
        if (req == null) return ExportResult.error("text/csv", "request is null");
        String title = sanitize(req.title);
        String body = sanitize(req.body);
        String csv = "title,body\n" + title + "," + body + "\n";
        return ExportResult.ok("text/csv", csv.getBytes(StandardCharsets.UTF_8));
    }

    private String sanitize(String s) {
        if (s == null) return "";
        return s.replace("\n", " ").replace(",", " ");
    }
}
