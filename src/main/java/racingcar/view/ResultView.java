package racingcar.view;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.History;

import java.util.List;
import java.util.stream.Collectors;

public class ResultView {

    public static void printResult(final History history, final List<Car> winners) {
        System.out.println("\n실행 결과");
        printCars(history);
        printWinners(winners);
    }

    private static void printCars(final History history) {
        final int tryCount = history.size();
        for (int i = 0; i < tryCount; i++) {
            getCarHistoryRow(history, i);
        }
    }

    private static void getCarHistoryRow(History history, int i) {
        Cars cars = history.get(i);
        List<Car> carsForHistory = cars.get();
        for (Car car : carsForHistory) {
            System.out.println(car.getName() + " : " + getDistanceIndicator(car));
        }
        System.out.println();
    }

    private static StringBuilder getDistanceIndicator(Car car) {
        StringBuilder stringBuilder = new StringBuilder();
        int totalDistance = car.getDistance();
        for (int i = 0; i < totalDistance; i++) {
            stringBuilder.append("-");
        }
        return stringBuilder;
    }

    private static void printWinners(List<Car> winners) {
        List<String> winnerNames = winners.stream()
                .map(Car::getName)
                .collect(Collectors.toList());
        System.out.println(String.join(", ", winnerNames) + " 가 최종 우승했습니다.");
    }
}