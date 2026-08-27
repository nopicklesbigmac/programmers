import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
class Solution {
    public int solution(String word, String[] pages) {
        String targetWord = word.toLowerCase();
        int n = pages.length;

        String[] myUrls = new String[n];
        double[] baseScores = new double[n];
        List<String>[] outboundLinks = new ArrayList[n];
        Map<String, Double> linkScores = new HashMap<>();
        Map<String, Integer> urlToIdx = new HashMap<>();

        for (int i = 0; i < n; i++) {
            outboundLinks[i] = new ArrayList<>();
            
            Pattern urlPattern = Pattern.compile("<meta property=\"og:url\" content=\"(https://[^\"]+)\"");
            Matcher urlMatcher = urlPattern.matcher(pages[i]);
            if (urlMatcher.find()) {
                myUrls[i] = urlMatcher.group(1);
                urlToIdx.put(myUrls[i], i);
            }

            Pattern bodyPattern = Pattern.compile("<body>([\\s\\S]*?)</body>");
            Matcher bodyMatcher = bodyPattern.matcher(pages[i]);
            String bodyContent = "";
            if (bodyMatcher.find()) {
                bodyContent = bodyMatcher.group(1);
            }

            Pattern wordPattern = Pattern.compile("[a-zA-Z]+");
            Matcher wordMatcher = wordPattern.matcher(bodyContent);
            int baseScore = 0;
            while (wordMatcher.find()) {
                if (wordMatcher.group().toLowerCase().equals(targetWord)) {
                    baseScore++;
                }
            }
            baseScores[i] = baseScore;

            Pattern linkPattern = Pattern.compile("<a href=\"(https://[^\"]+)\"");
            Matcher linkMatcher = linkPattern.matcher(bodyContent);
            while (linkMatcher.find()) {
                outboundLinks[i].add(linkMatcher.group(1));
            }
        }

        for (int i = 0; i < n; i++) {
            double outboundCount = outboundLinks[i].size();
            double giveScore = outboundCount > 0 ? baseScores[i] / outboundCount : 0.0;

            for (String targetUrl : outboundLinks[i]) {
                linkScores.put(targetUrl, linkScores.getOrDefault(targetUrl, 0.0) + giveScore);
            }
        }

        int bestIdx = -1;
        double maxMatchingScore = -1.0;

        for (int i = 0; i < n; i++) {
            double linkScore = linkScores.getOrDefault(myUrls[i], 0.0);
            double matchingScore = baseScores[i] + linkScore;

            if (matchingScore > maxMatchingScore) {
                maxMatchingScore = matchingScore;
                bestIdx = i;
            }
        }

        return bestIdx;
    }
}