package Server;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.search.*;
//import jdk.internal.icu.lang.UCharacterDirection;

import java.net.URL;
import java.nio.charset.StandardCharsets;

import java.io.*;
import java.util.Objects;
import java.util.Properties;



    public class Configurations {
        public static String[] names;

        private static Configurations MYinstance =  null;
        private static Properties prop= new Properties();
        InputStream input;
        //private static final String SOLVER_ALGORITHM = "Solver Algorithm";
        //private static final String MAZE_GENERATOR = "Maze Generator";
        private Configurations() {
            try{
//                URL url =  getClass().getResource();
                File directory = new File("./");
                String path = directory.getAbsolutePath().replace(".","");
                path = path + "resources\\config.properties";
                File file = new File(path);
                InputStream input= new FileInputStream(file);
//                FileInputStream input = Configurations.class.getResourceAsStream(file);
//                UCharacterDirection IOUtils;
//                String contents = IOUtils.toString(input, StandardCharsets.UTF_8);

                int content;
                String s = "";
                while ((content = input.read()) != -1)
                {
                    s = s + (char)content;

                }
                System.out.print(s);
                this.names = s.split("\r\n");

            }
            catch (IOException e)
            {

            }
        }
        public static Configurations getinstance()
        {
            if (MYinstance==null)
            {
                MYinstance= new Configurations();
                return MYinstance;
            }
            else
            {
                return MYinstance;
            }
        }

        public static AMazeGenerator Newmazegenerator() {
            String generator = names[0].split("=")[1];
                    //prop.getProperty("mazeGeneratingAlgorithm");
            switch (generator){
                case "My MazeGenerator" :
                    return new MyMazeGenerator();

                case "Simple Maze Generator" :
                    return new SimpleMazeGenerator();

                default:
                    return new EmptyMazeGenerator();
            }
        }

        public static ASearchingAlgorithm Newsearchalgo(){
            String algo  = names[1].split("=")[1];

            //prop.getProperty("mazeSearchingAlgorithm");

            switch (algo){
                case "Best first search" :
                    return new BestFirstSearch();

                case "Breadth first search" :
                    return new BreadthFirstSearch();
                case "Depth first search" :
                    return new DepthFirstSearch();
                default:
                    return new DepthFirstSearch();
            }

        }
        public static int getThredsNumber(){
            {
                String numm = names[2].split("=")[1];

                int num = Integer.parseInt(numm);
                if (null==null)
                {
                    return 1;
                }

                return num;

            }

            }
    }


