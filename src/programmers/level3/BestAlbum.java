// 프로그래머스 level3 베스트 엘범

package programmers.level3;

import java.util.*;

public class BestAlbum {

    static class Song implements Comparable<Song> {
        int id;
        int plays;

        public Song(int id, int plays) {
            this.id = id;
            this.plays = plays;
        }


        @Override
        public int compareTo(Song o) {
            return this.plays - o.plays;
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};

        Map<String, Integer> genresMap = new HashMap<>();
        Map<String, Queue<Song>> songs = new HashMap<>();

        for (int i = 0; i < plays.length; i++) {

            String genre = genres[i];

            if (!genresMap.containsKey(genre)) {
                genresMap.put(genre, plays[i]);
                songs.put(genre, new PriorityQueue<>(Comparator.reverseOrder()));
            }
            else {
                int play = genresMap.get(genre);
                genresMap.put(genre, play + plays[i]);
            }
            songs.get(genre).add(new Song(i, plays[i]));
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(genresMap.entrySet());
        sortGenre(list);

        List<Integer> result = new ArrayList<>();
        makeBestAlbum(songs, list, result);

        answer = new int[result.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

    private void makeBestAlbum(Map<String, Queue<Song>> songs, List<Map.Entry<String, Integer>> list, List<Integer> result) {
        Iterator<Map.Entry<String, Integer>> iter = list.iterator();
        while (iter.hasNext()) {
            String genre = iter.next().getKey();

            for (int i = 0; i < 2; i++) {
                Queue<Song> song = songs.get(genre);
                if (!song.isEmpty()) {
                    result.add(songs.get(genre).poll().id);
                }
            }
        }
    }

    private void sortGenre(List<Map.Entry<String, Integer>> list) {
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
    }
}
