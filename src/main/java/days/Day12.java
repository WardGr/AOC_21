package main.java.days;

import main.java.utilities.Utils.Utils;

import java.util.*;

public class Day12 implements Day {

    private Map<Cave, List<Cave>> caves;
    private Cave start = new Cave("start", false);

    public Day12(String file) {
        List<String> input = Utils.fileToList(file);
        createMap(input);
    }

    @Override
    public Object part1() {
        return countPaths(start, new HashSet<>());
    }

    @Override
    public Object part2() {
        return countPaths2(start, new HashSet<>());
    }

    private long countPaths(Cave cave, HashSet<Cave> set) {
        if (cave.getName().equals("end"))
            return 1;
        long count = 0;
        HashSet<Cave> current = (HashSet<Cave>) set.clone();
        current.add(cave);
        for (Cave toAdd : caves.get(cave)) {
            if ((toAdd.isBig() || !current.contains(toAdd)))
                count += countPaths(toAdd, current);
        }
        return count;
    }

    Cave error = new Cave("error", true);
    private long countPaths2(Cave cave, HashSet<Cave> set) {
        if (cave.getName().equals("end"))
            return 1;
        long count = 0;
        HashSet<Cave> current = (HashSet<Cave>) set.clone();
        if (current.contains(cave) && !cave.isBig())
            current.add(error);
        else
            current.add(cave);

        for (Cave toAdd : caves.get(cave)) {
            if (!(toAdd.getName().equals("start")) && (toAdd.isBig() || !current.contains(error) || !current.contains(toAdd)))
                count += countPaths2(toAdd, current);
        }
        return count;
    }

    public void printTest() {
        caves.keySet().forEach(cave -> {
            System.out.println("\n" + cave.name + ":");
            List<Cave> vals = new ArrayList<>(caves.get(cave));
            vals.forEach(val -> System.out.println(val.name));
        });
    }

    public void createMap(List<String> input) {
        caves = new HashMap<>();

        input.stream().map(line -> line.split("-")).forEach(cave -> {
                    caves.computeIfAbsent(new Cave(cave[0], cave[0].toUpperCase().equals(cave[0])), key ->
                            new ArrayList<>()).add(new Cave(cave[1], cave[1].toUpperCase().equals(cave[1])));
                    caves.computeIfAbsent(new Cave(cave[1], cave[1].toUpperCase().equals(cave[1])), key ->
                            new ArrayList<>()).add(new Cave(cave[0], cave[0].toUpperCase().equals(cave[0])));
                });
    }

    class Cave {
        private boolean big;
        private String name;

        public Cave(String name, boolean big) {
            this.name = name;
            this.big = big;
        }

        public String getName() {
            return this.name;
        }

        public boolean isBig() {
            return big;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cave cave = (Cave) o;
            return Objects.equals(name, cave.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}
