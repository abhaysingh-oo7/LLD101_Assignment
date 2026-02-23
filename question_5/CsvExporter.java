import java.nio.charset.StandardCharsets;

public class CsvExporter extends Exporter {

    @Override
    protected ExportResult doExport(ExportRequest req) {

        String body = req.body == null ? "" : req.body;

        String csv =
                "title,body\n" +
                escape(req.title) + "," +
                escape(body) + "\n";

        return new ExportResult("text/csv",
                csv.getBytes(StandardCharsets.UTF_8));
    }

    private String escape(String value) {
        if (value.contains(",") || value.contains("\n") || value.contains("\"")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }
}