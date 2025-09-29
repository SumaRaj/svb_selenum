package com.svb.qa.util;

import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.*;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotOnFailureExtension implements TestWatcher {

    private static final Path BASE_DIR = Paths.get("target", "screenshots");
    private static final DateTimeFormatter TS = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        try {
            Object testInstance = context.getRequiredTestInstance();
            WebDriver driver = extractWebDriver(testInstance);
            if (driver == null) {
                System.out.println("[ScreenshotOnFailure] No WebDriver 'driver' field found — skipping capture.");
                return;
            }

            String cls = context.getRequiredTestClass().getSimpleName();
            String method = context.getRequiredTestMethod().getName();
            String stamp = LocalDateTime.now().format(TS);

            Path dir = BASE_DIR.resolve(cls);
            Files.createDirectories(dir);

            // Safe filename
            String base = method + "_" + stamp;

            // Screenshot
            try {
                if (driver instanceof TakesScreenshot ts) {
                    byte[] png = ts.getScreenshotAs(OutputType.BYTES);
                    Path out = dir.resolve(base + ".png");
                    Files.write(out, png);
                    System.out.println("[ScreenshotOnFailure] Saved screenshot: " + out.toAbsolutePath());
                } else {
                    System.out.println("[ScreenshotOnFailure] Driver does not support screenshots.");
                }
            } catch (Exception e) {
                System.out.println("[ScreenshotOnFailure] Could not capture screenshot: " + e.getMessage());
            }

            // HTML source
            try {
                String html = driver.getPageSource();
                Path out = dir.resolve(base + ".html");
                Files.writeString(out, html);
                System.out.println("[ScreenshotOnFailure] Saved page source: " + out.toAbsolutePath());
            } catch (Exception e) {
                System.out.println("[ScreenshotOnFailure] Could not save page source: " + e.getMessage());
            }

            // Info (URL + title)
            try {
                String url = "";
                String title = "";
                try { url = driver.getCurrentUrl(); } catch (Exception ignored) {}
                try { title = driver.getTitle(); } catch (Exception ignored) {}
                Path out = dir.resolve(base + ".txt");
                Files.writeString(out, "URL: " + url + System.lineSeparator() + "Title: " + title);
                System.out.println("[ScreenshotOnFailure] Saved page info: " + out.toAbsolutePath());
            } catch (Exception e) {
                System.out.println("[ScreenshotOnFailure] Could not save page info: " + e.getMessage());
            }

        } catch (Exception outer) {
            System.out.println("[ScreenshotOnFailure] Unexpected error: " + outer.getMessage());
        }
    }

    private WebDriver extractWebDriver(Object testInstance) {
        try {
            Field field = findField(testInstance.getClass(), "driver");
            if (field == null) return null;
            field.setAccessible(true);
            Object obj = field.get(testInstance);
            return (obj instanceof WebDriver) ? (WebDriver) obj : null;
        } catch (Exception e) {
            return null;
        }
    }

    private Field findField(Class<?> cls, String name) {
        Class<?> cur = cls;
        while (cur != null) {
            try {
                return cur.getDeclaredField(name);
            } catch (NoSuchFieldException ignored) {
                cur = cur.getSuperclass();
            }
        }
        return null;
    }
}
