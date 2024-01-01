package hust.globalict.com.configuration;

import hust.globalict.com.utils.Constants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Configuration
public class LocaleConfiguration extends AcceptHeaderLocaleResolver implements WebMvcConfigurer {
	private List<Locale> locales = Arrays.asList(new Locale("vi"), new Locale("en"));

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		String headerLang = request.getHeader("Accept-Language");
		return headerLang == null || headerLang.isEmpty() ? Locale.getDefault()
				: Locale.lookup(Locale.LanguageRange.parse(headerLang), locales);
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
		rs.setDefaultLocale(Locale.forLanguageTag(Constants.DEFAULT_LOCALE));
		rs.setBasename(Constants.DEFAULT_BASENAME);
		rs.setDefaultEncoding(Constants.DEFAULT_ENCODING);
		rs.setCacheSeconds(60);
		rs.setUseCodeAsDefaultMessage(true);
		return rs;
	}
}
